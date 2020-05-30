package ru.vladimir.training.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //==================================================================================================================
    @NotBlank(message = "ПОЛЕ \"Наименование упражнения\" НЕ МОЖЕТ БЫТЬ ПУСТЫМ")
    @Length(max = 40, message = "СЛИШКОМ ДЛИННОЕ НАИМЕНОВАНИЕ УПРАЖНЕНИЯ (max 40 символов)")
    private String title;
    //==================================================================================================================
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    //==================================================================================================================
    public Topic() {

    }
    //==================================================================================================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //==================================================================================================================
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    //==================================================================================================================
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
