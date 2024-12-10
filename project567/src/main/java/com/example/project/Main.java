package com.example.project;

public class Main {
    public static void main(String[] args) {
        TaskManagementSystem system = new TaskManagementSystem();

        system.addUser("Alice", "Developer");
        system.addUser("Bob", "Tester");

        int task1 = system.createTask("Fix login bug", "Alice", 2);
        int task2 = system.createTask("Write unit tests", "Bob", 3);

        system.updateTaskStatus(task1, TaskStatus.IN_PROGRESS);
        system.updateTaskPriority(task2, 5);

        system.listTasksByPriority().forEach(System.out::println);
    }
}