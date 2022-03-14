package com.example.userstories.core;

import com.example.userstories.core.tasks.Task;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskTest {

    @Test
    public void testTaskShouldBeCreated() {
        Task task = new Task();
    }

    @Test
}
