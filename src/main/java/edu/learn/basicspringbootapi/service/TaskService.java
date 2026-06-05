package edu.learn.basicspringbootapi.service;

import edu.learn.basicspringbootapi.dto.TaskRequestDto;
import edu.learn.basicspringbootapi.dto.TaskResponseDto;
import edu.learn.basicspringbootapi.model.Task;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto taskRequestDto);
    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto);
    void deleteTask(Long id);
    TaskResponseDto getTask(Long id);
    List<TaskResponseDto> getTasks();
}
