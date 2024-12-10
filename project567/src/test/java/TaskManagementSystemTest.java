
import org.junit.Test;

import com.example.project.Task;
import com.example.project.TaskManagementSystem;
import com.example.project.TaskStatus;
import com.example.project.User;

import java.util.List;

import static org.junit.Assert.*;

public class TaskManagementSystemTest {

    @Test
    public void testAddUserSuccessfully() {
        TaskManagementSystem system = new TaskManagementSystem();
        system.addUser("Alice", "Developer");
        int taskId = system.createTask("Fix login issue", "Alice", 2);
        assertNotNull(system.getTask(taskId));
    }

    @Test
    public void testAddUserInvalidInput() {
        TaskManagementSystem system = new TaskManagementSystem();
        try {
            system.addUser("", "Developer");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("User name and role cannot be empty", e.getMessage());
        }
    }

    @Test
    public void testCreateTaskSuccessfully() {
        TaskManagementSystem system = new TaskManagementSystem();
        system.addUser("Bob", "Tester");
        int taskId = system.createTask("Write unit tests", "Bob", 1);
        assertEquals(1, taskId);
        assertEquals("Write unit tests", system.getTask(taskId).getDescription());
    }

    @Test
    public void testCreateTaskWithInvalidUser() {
        TaskManagementSystem system = new TaskManagementSystem();
        try {
            system.createTask("Invalid task", "UnknownUser", 1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Assigned user not found", e.getMessage());
        }
    }

    @Test
    public void testUpdateTaskStatus() {
        TaskManagementSystem system = new TaskManagementSystem();
        system.addUser("Charlie", "Manager");
        int taskId = system.createTask("Plan project phases", "Charlie", 4);
        system.updateTaskStatus(taskId, TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, system.getTask(taskId).getStatus());
    }

    @Test
    public void testUpdateTaskPriority() {
        TaskManagementSystem system = new TaskManagementSystem();
        system.addUser("Alice", "Developer");
        int taskId = system.createTask("Fix database bug", "Alice", 2);
        system.updateTaskPriority(taskId, 5);
        assertEquals(5, system.getTask(taskId).getPriority());
    }

    @Test
    public void testListTasksByPriority() {
        TaskManagementSystem system = new TaskManagementSystem();
        system.addUser("Alice", "Developer");
        system.addUser("Bob", "Tester");
        system.createTask("Task 1", "Alice", 1);
        system.createTask("Task 2", "Bob", 3);
        List<Task> tasks = system.listTasksByPriority();
        assertEquals(3, tasks.get(0).getPriority());
        assertEquals(1, tasks.get(1).getPriority());
    }

    @Test
    public void testListTasksEmpty() {
        TaskManagementSystem system = new TaskManagementSystem();
        List<Task> tasks = system.listTasks();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void testGetTaskById() {
        TaskManagementSystem system = new TaskManagementSystem();
        system.addUser("Alice", "Developer");
        int taskId = system.createTask("Implement search feature", "Alice", 3);
        Task task = system.getTask(taskId);
        assertEquals("Implement search feature", task.getDescription());
    }

    @Test
    public void testGetTaskByInvalidId() {
        TaskManagementSystem system = new TaskManagementSystem();
        try {
            system.getTask(999);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Task not found", e.getMessage());
        }
    }

    @Test
    public void testTaskStringRepresentation() {
        Task task = new Task(1, "Test String Method", "Alice", TaskStatus.PENDING, 2);
        String expected = "Task{id=1, description='Test String Method', assignedTo='Alice', status=PENDING, priority=2}";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testUserStringRepresentation() {
        User user = new User("Bob", "Tester");
        String expected = "User{name='Bob', role='Tester'}";
        assertEquals(expected, user.toString());
    }

    @Test
    public void testTaskPrioritySorting() {
        TaskManagementSystem system = new TaskManagementSystem();
        system.addUser("Alice", "Developer");
        system.createTask("Low priority task", "Alice", 1);
        system.createTask("High priority task", "Alice", 5);
        List<Task> tasks = system.listTasksByPriority();
        assertEquals("High priority task", tasks.get(0).getDescription());
    }

    @Test
    public void testMultipleTaskUpdates() {
        TaskManagementSystem system = new TaskManagementSystem();
        system.addUser("Charlie", "Manager");
        int taskId = system.createTask("Deploy to production", "Charlie", 3);
        system.updateTaskStatus(taskId, TaskStatus.IN_PROGRESS);
        system.updateTaskPriority(taskId, 4);
        Task task = system.getTask(taskId);
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        assertEquals(4, task.getPriority());
    }

    @Test
    public void testAddingDuplicateUsers() {
        TaskManagementSystem system = new TaskManagementSystem();
        system.addUser("Alice", "Developer");
        system.addUser("Alice", "Tester");
        assertNotNull(system.createTask("Fix bug", "Alice", 2));
    }
}
