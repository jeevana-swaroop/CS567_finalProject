package com.example.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskManagementSystem {
    private List<Task> tasks = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private int taskIdCounter = 1;

    public void addUser(String name, String role) {
        if (name == null || name.isEmpty() || role == null || role.isEmpty()) {
            throw new IllegalArgumentException("User name and role cannot be empty");
        }
        users.add(new User(name, role));
    }

    public int createTask(String description, String assignedTo, int priority) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty");
        }
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getName().equals(assignedTo))
                .findFirst();
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("Assigned user not found");
        }
        Task task = new Task(taskIdCounter++, description, assignedTo, TaskStatus.PENDING, priority);
        tasks.add(task);
        return task.getId();
    }

    public void updateTaskStatus(int taskId, TaskStatus status) {
        Task task = getTask(taskId);
        task.setStatus(status);
    }

    public void updateTaskPriority(int taskId, int priority) {
        Task task = getTask(taskId);
        task.setPriority(priority);
    }

    public List<Task> listTasksByPriority() {
        tasks.sort((t1, t2) -> Integer.compare(t2.getPriority(), t1.getPriority()));
        return tasks;
    }

    public Task getTask(int taskId) {
        return tasks.stream()
                .filter(task -> task.getId() == taskId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    public List<Task> listTasks() {
        return new ArrayList<>(tasks);
    }
}
