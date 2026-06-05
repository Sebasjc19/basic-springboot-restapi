package edu.learn.basicspringbootapi.service;

import edu.learn.basicspringbootapi.dto.TaskRequestDto;
import edu.learn.basicspringbootapi.dto.TaskResponseDto;
import edu.learn.basicspringbootapi.model.Task;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements TaskService{

    private final List<Task> taskList;
    @NotNull private Long ids = 0L;

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Task task = new Task(
                ids++,
                taskRequestDto.title(),
                taskRequestDto.description(),
                taskRequestDto.completed(),
                LocalDate.now()
                );
        taskList.add(task);
        return toResponse(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskList.stream().filter(x -> x.getId().equals(id))
                .findFirst().orElseThrow();

        task.setTitle(taskRequestDto.title());
        task.setDescription(taskRequestDto.description());
        task.setCompleted(taskRequestDto.completed());

        return toResponse(task);
    }

    @Override
    public void deleteTask(Long id) {
        getTask(id);
        taskList.remove(id);
    }

    @Override
    public TaskResponseDto getTask(Long id) {
        Task task = taskList.stream().filter(x -> x.getId().equals(id))
                .findAny().orElseThrow();
        return toResponse(task);
    }

    @Override
    public List<TaskResponseDto> getTasks() {
        return taskList.stream().map(this::toResponse).toList();
    }

    private TaskResponseDto toResponse(Task task){
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getCreatedAt()
        );
    }
}
