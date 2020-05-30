package ru.vladimir.training.repo;

import org.springframework.data.repository.CrudRepository;
import ru.vladimir.training.domain.Task;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {
    List<Task> findAll();

    Task findByContent(String content);
}
