package com.example.simpletodo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TaskFragment extends Fragment {

    private ArrayList<TaskItem> activeTasks = new ArrayList<>();
    private ArrayList<TaskItem> completedTasks = new ArrayList<>();
    private TaskAdapter activeAdapter;
    private TaskAdapter completedAdapter;

    private TextView completedHeader;

    private static final String ARG_CATEGORY = "category";
    private String category;

    public TaskFragment() {}

    public static TaskFragment newInstance(String category) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        ListView activeListView = view.findViewById(R.id.taskList);
        ListView completedListView = view.findViewById(R.id.completedTaskList);
        completedHeader = view.findViewById(R.id.completedHeader);

        activeAdapter = new TaskAdapter(requireContext(), activeTasks, this::toggleTaskStatus);
        completedAdapter = new TaskAdapter(requireContext(), completedTasks, this::toggleTaskStatus);

        activeListView.setAdapter(activeAdapter);
        completedListView.setAdapter(completedAdapter);

        updateCompletedVisibility();

        return view;
    }

    private void toggleTaskStatus(TaskItem task) {
        if (!task.isCompleted()) {  // <-- It hasn't been updated yet
            activeTasks.remove(task);
            task.setCompleted(true);
            completedTasks.add(task);
        } else {
            completedTasks.remove(task);
            task.setCompleted(false);
            activeTasks.add(task);
        }

        activeAdapter.notifyDataSetChanged();
        completedAdapter.notifyDataSetChanged();
        updateCompletedVisibility();
    }


    private void updateCompletedVisibility() {
        if (completedTasks.isEmpty()) {
            completedHeader.setVisibility(View.GONE);
        } else {
            completedHeader.setVisibility(View.VISIBLE);
        }
    }

    public void showAddTaskDialog() {
        EditText input = new EditText(requireContext());
        input.setHint("Task name");
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(requireContext())
                .setTitle("New Task")
                .setMessage("Enter a task for " + category + ":")
                .setView(input)
                .setPositiveButton("Add", (dialog, which) -> {
                    String taskText = input.getText().toString().trim();
                    if (!taskText.isEmpty()) {
                        TaskItem task = new TaskItem(taskText, false);
                        activeTasks.add(task);
                        activeAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(requireContext(), "Task cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
