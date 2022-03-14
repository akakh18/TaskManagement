package com.example.userstories.core;

import com.example.userstories.core.tasks.Task;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.userstories.core.tasks.Status.DONE;

@SpringBootTest
public class TaskTest {
    private final int MAX_ASSIGNEES_NUMBER = 20;

    @Test
    public void testTaskShouldBeCreated() {
        Task task = new Task();
    }

    @Test
    public void testInitialTaskShouldHaveInitialStatus() {
        Task task = new Task();
        assert task.getStatus().equals("NOT_DONE");
    }

    @Test
    public void testStatusCanBeChanged() {
        Task task = new Task();
        task.setStatus(DONE);

        assert task.getStatus().equals("DONE");
    }

    private int randomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void addAssignees(Task task, int numAssignees) {
        for (int i = 0; i < numAssignees; ++i) {
            task.setAssignee(String.format("assignee_%d", i));
        }
    }

    @Test
    public void testTaskShouldHaveAssignees() {
        Task task = new Task();
        int numAssignees = randomNumber(0, MAX_ASSIGNEES_NUMBER);
        addAssignees(task, numAssignees);

        assert task.getAssignees().size() == numAssignees;
    }

    @Test
    public void testTaskShouldHaveCorrectAssignees() {
        Task task = new Task();
        int numAssignees = randomNumber(0, MAX_ASSIGNEES_NUMBER);
        addAssignees(task, numAssignees);

        String testAssignee = String.format("assignee_%d", randomNumber(0, numAssignees));

        assert task.getAssignees().contains(testAssignee);
    }

    @Test
    public void testTaskShouldHaveAuthor() {
        Task task = new Task();
        String authorName = "author";
        task.setAuthor(authorName);

        assert task.getAuthor().equals(authorName);
    }
}
