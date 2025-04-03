package com.example.simpletodo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private FloatingActionButton fabAdd;
    private TabAdapter tabAdapter;

    private ArrayList<String> tabNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fabAdd = findViewById(R.id.fabAddTask);

        // Default tabs
        tabNames.add("Personal");
        tabNames.add("School");

        tabAdapter = new TabAdapter(this, tabNames);
        viewPager.setAdapter(tabAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabNames.get(position))
        ).attach();

        fabAdd.setOnClickListener(v -> showAddTaskDialog());
        tabLayout.setOnLongClickListener(v -> {
            showAddCategoryDialog();
            return true;
        });
    }

    private void showAddTaskDialog() {
        int position = tabLayout.getSelectedTabPosition();
        TaskFragment currentFragment = tabAdapter.getFragmentAt(position);
        if (currentFragment != null) {
            currentFragment.showAddTaskDialog();
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAddCategoryDialog() {
        final android.widget.EditText input = new android.widget.EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("New Category")
                .setMessage("Enter category name:")
                .setView(input)
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = input.getText().toString().trim();
                    if (!name.isEmpty()) {
                        tabNames.add(name);
                        tabAdapter.notifyTabsChanged(tabNames);
                        new TabLayoutMediator(tabLayout, viewPager,
                                (tab, position) -> tab.setText(tabNames.get(position))
                        ).attach();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
