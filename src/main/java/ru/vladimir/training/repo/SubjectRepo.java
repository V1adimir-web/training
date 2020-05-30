package ru.vladimir.training.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import ru.vladimir.training.domain.Subject;

import java.util.List;

public interface SubjectRepo extends CrudRepository<Subject, Long> {

    List<Subject> findAll();

    List<Subject> findAllBySubjectname(String subjectname);

    List<Subject> findBySubjectnameContainingOrderBySubjectname(String subjectname);

    Subject findBySubjectname(String subjectname);

    //Subject findById(Long id);
    //void save(Subject subject);
}
