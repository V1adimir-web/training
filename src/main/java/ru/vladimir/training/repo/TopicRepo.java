package ru.vladimir.training.repo;

import org.springframework.data.repository.CrudRepository;
import ru.vladimir.training.domain.Topic;

import java.util.List;

public interface TopicRepo extends CrudRepository<Topic, Long> {
    List<Topic> findAll();

    Topic findByTitle(String title);
}
