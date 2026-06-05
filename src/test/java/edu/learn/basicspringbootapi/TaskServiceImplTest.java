package edu.learn.basicspringbootapi;

import edu.learn.basicspringbootapi.dto.TaskRequestDto;
import edu.learn.basicspringbootapi.dto.TaskResponseDto;
import edu.learn.basicspringbootapi.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceImplTest {

    private final TaskServiceImpl taskService = new TaskServiceImpl(
            new ArrayList<>()
    );

    @Test
    void createTaskTest(){
        TaskRequestDto taskRequestDto = new TaskRequestDto(
                "Task 1",
                "Description 1",
                false
        );

        TaskResponseDto taskResponseDto = taskService.createTask(taskRequestDto);
        assertEquals("Task 1", taskResponseDto.title());
    }

    @Test
    void updateTaskTest(){
        TaskRequestDto createDto = new TaskRequestDto(
                "Task 1",
                "Description 1",
                false
        );
        TaskResponseDto task = taskService.createTask(createDto);

        TaskRequestDto updateDto = new TaskRequestDto(
                "Task updated",
                "Description updated",
                true
        );

        TaskResponseDto updatedTask = taskService.updateTask(task.id(),updateDto);

        assertTrue(updatedTask.completed());
    }

    @Test
    void deleteTaskTest(){
        TaskRequestDto createDto = new TaskRequestDto(
                "Task 1",
                "Description 1",
                false
        );
        TaskResponseDto task = taskService.createTask(createDto);

        taskService.deleteTask(task.id());
        assertEquals(0, taskService.getTasks().size());
    }

    @Test
    void getTaskTest(){
        TaskRequestDto createDto = new TaskRequestDto(
                "Task 1",
                "Description 1",
                false
        );
        TaskResponseDto task = taskService.createTask(createDto);

        TaskResponseDto taskResponseDto = taskService.getTask(task.id());

        assertEquals(task, taskResponseDto);
    }

    @Test
    void getTasksTest(){
        TaskRequestDto createDto = new TaskRequestDto(
                "Task 1",
                "Description 1",
                false
        );
        taskService.createTask(createDto);

        createDto = new TaskRequestDto(
                "Task 2",
                "Description 2",
                false
        );
        taskService.createTask(createDto);

        createDto = new TaskRequestDto(
                "Task 3",
                "Description 3",
                false
        );
        taskService.createTask(createDto);

        assertEquals(3, taskService.getTasks().size());
    }
}
