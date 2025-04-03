package com.example.simpletodo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class TabAdapter extends FragmentStateAdapter {

    private ArrayList<String> tabTitles;
    private final HashMap<Integer, TaskFragment> fragmentMap = new HashMap<>();

    public TabAdapter(@NonNull FragmentActivity fa, ArrayList<String> titles) {
        super(fa);
        this.tabTitles = new ArrayList<>(titles);
    }

    public void notifyTabsChanged(ArrayList<String> newTitles) {
        this.tabTitles = new ArrayList<>(newTitles);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        TaskFragment fragment = TaskFragment.newInstance(tabTitles.get(position));
        fragmentMap.put(position, fragment);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return tabTitles.size();
    }

    public TaskFragment getFragmentAt(int position) {
        return fragmentMap.get(position);
    }
}
