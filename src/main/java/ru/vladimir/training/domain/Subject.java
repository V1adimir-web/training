package ru.vladimir.training.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //==================================================================================================================
    @NotBlank(message = "ПОЛЕ \"Наименование предмета\" НЕ МОЖЕТ БЫТЬ ПУСТЫМ!")
    @Length(max = 35, message = "СЛИШКОМ ДЛИННОЕ НАИМЕНОВАНИЕ ПРЕДМЕТА (max 35 символов)")
    private String subjectname;
    //==================================================================================================================
    public Subject() {
    }
    //==================================================================================================================
    // @NotBlank(message = "Поле Наименование предмета не может быть пустым!") @Length(max = 35, message = "Слишком длинное наменование (max 35 символов)")
    //public Subject(String subjectname) {
    //    this.subjectname = subjectname;
    //}
    //==================================================================================================================
    public Long getId() {
        return id;
    }
    //==================================================================================================================
    public void setId(Long id) {
        this.id = id;
    }
    //==================================================================================================================
    public String getSubjectname() {
        return subjectname;
    }
    //==================================================================================================================
    public void setSubjectname(String name) {
        this.subjectname = name;
    }
}
