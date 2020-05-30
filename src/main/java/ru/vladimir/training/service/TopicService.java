package ru.vladimir.training.service;

import org.springframework.stereotype.Service;
import ru.vladimir.training.domain.Subject;
import ru.vladimir.training.domain.Topic;
import ru.vladimir.training.repo.SubjectRepo;
import ru.vladimir.training.repo.TopicRepo;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepo topicRepo;
    private final SubjectRepo subjectRepo;
    public TopicService(TopicRepo topicRepo, SubjectRepo subjectRepo) {
        this.topicRepo = topicRepo;
        this.subjectRepo = subjectRepo;
    }
    //==================================================================================================================
    public List<Topic> findTopics(String filter) {
        if (filter != null && !filter.isEmpty()) {
            return topicRepo.findByTitleContainingOrderByTitle(filter);
        }
        return topicRepo.findAll();
    }
    //==================================================================================================================
    public boolean addTopic(Topic topic, String subjectname) {
        if (isTopicExist(topic)) return false;
        setSubjectForTopic(topic, subjectname);
        topicRepo.save(topic);
        return true;
    }
    //==================================================================================================================
    public boolean updTopic(Topic topic, Topic oldTopic, String subjectname) {
        if (topic.getTitle().trim().equals(oldTopic.getTitle())) {
            return true;
        }
        if (isTopicExist(topic)) return false;
        setSubjectForTopic(topic, subjectname);
        topic.setId(oldTopic.getId());
        topicRepo.save(topic);
        return true;
    }
    //==================================================================================================================
    private void setSubjectForTopic(Topic topic, String subjectname) {
        Subject selectedSubject = subjectRepo.findBySubjectname(subjectname);
        if (selectedSubject != null) {
            topic.setSubject(selectedSubject);
        }
    }
    //==================================================================================================================
    private boolean isTopicExist(Topic topic) {
        Topic topicFromDb = topicRepo.findByTitle(topic.getTitle().trim());
        return topicFromDb != null;
    }
    //==================================================================================================================
    public void removeTopic(Topic topic) {
        if (topicRepo.findById(topic.getId()).isPresent()) {
            topicRepo.delete(topic);
        }
    }
}
