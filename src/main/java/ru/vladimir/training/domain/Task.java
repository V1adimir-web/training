package ru.vladimir.training.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //==================================================================================================================
    @NotBlank(message = "ПОЛЕ \"Условие задачи\" НЕ МОЖЕТ БЫТЬ ПУСТЫМ")
    @Length(max = 2048, message = "СЛИШКОМ ДЛИННОЕ УСЛОВИЕ ЗАДАЧИ (max 2 кбайта)")
    private String content;
    //==================================================================================================================
    @NotBlank(message = "ПОЛЕ \"Правильный ответ\" НЕ МОЖЕТ БЫТЬ ПУСТЫМ")
    @Length(max = 30, message = "СЛИШКОМ ДЛИННЫЙ ТЕКСТ ПРАВИЛЬНОГО ОТВЕТА (max 30 символов)")
    private String rightanswer;
    //==================================================================================================================
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    //==================================================================================================================
    public Task() {

    }
    //==================================================================================================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //==================================================================================================================
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    //==================================================================================================================
    public String getRightanswer() {
        return rightanswer;
    }

    public void setRightanswer(String rightanswer) {
        this.rightanswer = rightanswer;
    }
    //==================================================================================================================
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
