package com.example.parserapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="PARSE_DATA")
public class ParserEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "parse_seq")
     @SequenceGenerator(name="parse_seq",sequenceName = "PARSE_SEQ",allocationSize = 1)
      @Column(name="ID")
    private Long id;
     @Column(name="NAME")
     private String name;
     @Column(name="AGE")
     private Integer age;
     @Column(name="EMAIL")
     private String email;
     @Column(name="DEPARTMENT")
     private String department;
      @Column(name="PROCESSED_AT")
     private String processedAt;
       public ParserEntity()
       {

       }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
    }
}
