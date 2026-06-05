package edu.learn.basicspringbootapi.controller;

import edu.learn.basicspringbootapi.dto.TaskRequestDto;
import edu.learn.basicspringbootapi.dto.TaskResponseDto;
import edu.learn.basicspringbootapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping()
    private ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto){
        return ResponseEntity.ok(taskService.createTask(taskRequestDto));
    }

    @PutMapping("/{id}")
    private ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto taskRequest){
        return ResponseEntity.ok(taskService.updateTask(id,taskRequest));
    }

    @GetMapping("/{id}")
    private ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @GetMapping()
    private ResponseEntity<List<TaskResponseDto>> getTasks(){
        return ResponseEntity.ok(taskService.getTasks());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> removeTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Deleted successfully");
    }

}
