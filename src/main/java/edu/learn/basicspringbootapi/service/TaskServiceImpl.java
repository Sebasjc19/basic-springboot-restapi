package edu.learn.basicspringbootapi.service;

import edu.learn.basicspringbootapi.dto.TaskRequestDto;
import edu.learn.basicspringbootapi.dto.TaskResponseDto;
import edu.learn.basicspringbootapi.model.Task;
import edu.learn.basicspringbootapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.title());
        task.setDescription(taskRequestDto.description());
        task.setCompleted(taskRequestDto.completed());
        task.setCreatedAt(LocalDate.now());
        task = taskRepository.save(task);
        return toResponse(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(taskRequestDto.title());
        task.setDescription(taskRequestDto.description());
        task.setCompleted(taskRequestDto.completed());
        task = taskRepository.save(task);
        return toResponse(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(task);
    }

    @Override
    public TaskResponseDto getTask(Long id) {
        return toResponse(taskRepository.findById(id).orElseThrow());
    }

    @Override
    public List<TaskResponseDto> getTasks() {
        return taskRepository.findAll().stream().map(this::toResponse).toList();
    }

    private TaskResponseDto toResponse(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getCreatedAt()
        );
    }
}
