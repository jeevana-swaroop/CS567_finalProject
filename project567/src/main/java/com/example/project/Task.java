package com.example.project;

public class Task {
    private int id;
    private String description;
    private String assignedTo;
    private TaskStatus status;
    private int priority;

    public Task(int id, String description, String assignedTo, TaskStatus status, int priority) {
        this.id = id;
        this.description = description;
        this.assignedTo = assignedTo;
        this.status = status;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                '}';
    }
}
