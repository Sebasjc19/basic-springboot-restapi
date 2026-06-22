package edu.learn.basicspringbootapi.repository;

import edu.learn.basicspringbootapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
