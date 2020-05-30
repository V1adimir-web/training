package ru.vladimir.training.service;

import org.springframework.stereotype.Service;
import ru.vladimir.training.domain.Task;
import ru.vladimir.training.domain.Topic;
import ru.vladimir.training.repo.TaskRepo;
import ru.vladimir.training.repo.TopicRepo;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final TopicRepo topicRepo;
    public TaskService(TaskRepo taskRepo, TopicRepo topicRepo) {
        this.taskRepo = taskRepo;
        this.topicRepo = topicRepo;
    }
    //==================================================================================================================
    public List<Task> findAll() {
        return taskRepo.findAll();
    }
    //==================================================================================================================
    private boolean isTaskExist(Task task) {
        Task taskFromDb = taskRepo.findByContent(task.getContent().trim());
        return taskFromDb != null;
    }
    //==================================================================================================================
    private void setTopicForTask(Task task, String topictitle) {
        Topic selectedTopic = topicRepo.findByTitle(topictitle);
        if (selectedTopic != null) {
            task.setTopic(selectedTopic);
        }
    }
    //==================================================================================================================
    public boolean addTask(Task task, String topictitle) {
        if (isTaskExist(task)) return false;
        setTopicForTask(task, topictitle);
        taskRepo.save(task);
        return true;
    }
    //==================================================================================================================
    public boolean updTask(Task task, Task oldTask, String topictitle) {
        if (task.getContent().trim().equals(oldTask.getContent())) {
            return true;
        }
        if (isTaskExist(task)) return false;
        setTopicForTask(task, topictitle);
        task.setId(oldTask.getId());
        taskRepo.save(task);
        return true;
    }
    //==================================================================================================================
    public void removeTask(Task task) {
        if (taskRepo.findById(task.getId()).isPresent()) {
            taskRepo.delete(task);
        }
    }
}
