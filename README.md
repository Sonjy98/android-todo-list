SimpleToDo
SimpleToDo is an Android application built with Java that allows users to manage tasks efficiently across multiple categories. The app features a tabbed layout, dynamic task lists, and a custom adapter to handle task interactions such as marking items as completed or active.

Features
Organize tasks by category using a tabbed interface (ViewPager2 + TabLayout)
Add new tasks through a clean, user-friendly dialog
Mark tasks as completed using checkboxes
Completed tasks are automatically moved to a separate section
Unchecking a completed task moves it back to the active list
The “Completed” section only appears when relevant

Technologies Used:
Java
Android Studio
API Level 33
ViewPager2, TabLayout, ListView
Custom ArrayAdapter for task list handling

How It Works
Each category is represented by a tab. Users can add tasks to the selected category and interact with them through checkboxes. Completed tasks are moved to a separate list that appears dynamically only when needed. A custom adapter ensures proper logic and layout behavior for toggling task status.

Setup Instructions
Clone this repository
Open the project in Android Studio
Run on an emulator or device with API level 33 or higher

Project Structure
```
com.example.simpletodo/
├── MainActivity.java
├── TaskFragment.java
├── TaskAdapter.java
├── TaskItem.java
res/layout/
├── activity_main.xml
├── fragment_task.xml
└── item_task.xml
```
Potential Improvements
Add persistent storage (Room DB or SharedPreferences)
Support for task editing and due dates
Category management
