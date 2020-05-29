package ru.vladimir.training.service;

import org.springframework.stereotype.Service;
import ru.vladimir.training.domain.Subject;
import ru.vladimir.training.repo.SubjectRepo;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepo subjectRepo;
    public SubjectService(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }
    //==================================================================================================================
    public List<Subject> findAll() {
        return subjectRepo.findAll();
    }
    //==================================================================================================================
    public boolean addSubject(Subject subject) {
        Subject subjectFromDb = subjectRepo.findBySubjectname(subject.getSubjectname().trim());
        if (subjectFromDb != null) {
            return false;
        }
        subjectRepo.save(subject);
        return true;
    }
    //==================================================================================================================
    public boolean updSubject(Subject subject, Subject oldSubject) {
        if (subject.getSubjectname().trim().equals(oldSubject.getSubjectname())) {
            return true;
        }
        Subject subjectFromDb = subjectRepo.findBySubjectname(subject.getSubjectname());
        if (subjectFromDb != null) {
            return false;
        } else {
            subject.setId(oldSubject.getId());
            subjectRepo.save(subject);
            return true;
        }
    }
    //==================================================================================================================
    public void removeSubject(Subject subject) {
        if (subjectRepo.findById(subject.getId()).isPresent()) {
            subjectRepo.delete(subject);
        }
    }
}
