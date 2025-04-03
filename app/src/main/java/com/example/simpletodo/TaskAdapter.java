package com.example.simpletodo;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<TaskItem> {
    private final List<TaskItem> tasks;
    private final OnTaskToggleListener toggleListener;

    public interface OnTaskToggleListener {
        void onTaskToggled(TaskItem task);
    }

    public TaskAdapter(Context context, List<TaskItem> tasks, OnTaskToggleListener listener) {
        super(context, 0, tasks);
        this.tasks = tasks;
        this.toggleListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TaskItem task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        TextView textView = convertView.findViewById(R.id.taskText);

        // Detach listener before setting checkbox state
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(task.isCompleted());

        textView.setText(task.getText());

        // Optional: visually indicate completed task
        if (task.isCompleted()) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            textView.setAlpha(0.5f);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            textView.setAlpha(1.0f);
        }

        // Listener that moves the task and then updates status
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            toggleListener.onTaskToggled(task); // handle removal + reinsertion
        });

        return convertView;
    }
}
