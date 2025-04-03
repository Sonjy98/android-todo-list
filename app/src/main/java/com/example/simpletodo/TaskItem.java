package com.example.simpletodo;

public class TaskItem {
    private String text;
    private boolean isCompleted;

    public TaskItem(String text, boolean isCompleted) {
        this.text = text;
        this.isCompleted = isCompleted;
    }

    public String getText() {
        return text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
