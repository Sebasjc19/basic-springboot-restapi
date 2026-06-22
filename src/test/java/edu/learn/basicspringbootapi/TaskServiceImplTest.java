package edu.learn.basicspringbootapi;

import edu.learn.basicspringbootapi.dto.TaskRequestDto;
import edu.learn.basicspringbootapi.dto.TaskResponseDto;
import edu.learn.basicspringbootapi.model.Task;
import edu.learn.basicspringbootapi.repository.TaskRepository;
import edu.learn.basicspringbootapi.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void createTaskTest() {
        TaskRequestDto taskRequestDto = new TaskRequestDto("Task 1", "Description 1", false);

        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
            Task task = invocation.getArgument(0);
            task.setId(1L);
            return task;
        });

        TaskResponseDto taskResponseDto = taskService.createTask(taskRequestDto);
        assertEquals("Task 1", taskResponseDto.title());
    }

    @Test
    void updateTaskTest() {
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Task 1");
        existingTask.setDescription("Description 1");
        existingTask.setCompleted(true);
        existingTask.setCreatedAt(LocalDate.now());

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TaskRequestDto updateDto = new TaskRequestDto("Task updated", "Description updated", true);
        TaskResponseDto updatedTask = taskService.updateTask(1L, updateDto);

        assertTrue(updatedTask.completed());
    }

    @Test
    void deleteTaskTest() {
        AtomicLong idCounter = new AtomicLong(1);
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
            Task task = invocation.getArgument(0);
            task.setId(idCounter.getAndIncrement());
            return task;
        });

        TaskResponseDto task = taskService.createTask(new TaskRequestDto("Task 1", "Description 1", false));

        Task entity = new Task();
        entity.setId(task.id());
        entity.setTitle("Task 1");
        entity.setDescription("Description 1");
        entity.setCompleted(false);
        entity.setCreatedAt(task.createdAt());

        when(taskRepository.findById(task.id())).thenReturn(Optional.of(entity));
        doNothing().when(taskRepository).delete(entity);
        when(taskRepository.findAll()).thenReturn(List.of());

        taskService.deleteTask(task.id());
        assertEquals(0, taskService.getTasks().size());
    }

    @Test
    void getTaskTest() {
        AtomicLong idCounter = new AtomicLong(1);
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
            Task task = invocation.getArgument(0);
            task.setId(idCounter.getAndIncrement());
            return task;
        });

        TaskRequestDto createDto = new TaskRequestDto("Task 1", "Description 1", false);
        TaskResponseDto task = taskService.createTask(createDto);

        Task entity = new Task();
        entity.setId(task.id());
        entity.setTitle(task.title());
        entity.setDescription(task.description());
        entity.setCompleted(task.completed());
        entity.setCreatedAt(task.createdAt());

        when(taskRepository.findById(task.id())).thenReturn(Optional.of(entity));

        TaskResponseDto taskResponseDto = taskService.getTask(task.id());
        assertEquals(task, taskResponseDto);
    }

    @Test
    void getTasksTest() {
        AtomicLong idCounter = new AtomicLong(1);
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
            Task task = invocation.getArgument(0);
            task.setId(idCounter.getAndIncrement());
            return task;
        });

        taskService.createTask(new TaskRequestDto("Task 1", "Description 1", false));
        taskService.createTask(new TaskRequestDto("Task 2", "Description 2", false));
        taskService.createTask(new TaskRequestDto("Task 3", "Description 3", false));

        when(taskRepository.findAll()).thenReturn(List.of(
                createEntity(1L, "Task 1", "Description 1"),
                createEntity(2L, "Task 2", "Description 2"),
                createEntity(3L, "Task 3", "Description 3")
        ));

        assertEquals(3, taskService.getTasks().size());
    }

    private Task createEntity(Long id, String title, String description) {
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setCompleted(false);
        task.setCreatedAt(LocalDate.now());
        return task;
    }
}
