package com.school.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "([a-zA-Z]+[\\s]+[a-zA-Z]+)")
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "[055]+[0-9]{7}")
    private String mobile;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentClassroom> studentClassrooms = new HashSet<>();
    public Student() {}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Set<StudentClassroom> getStudentClassrooms() {
        return studentClassrooms;
    }

    public void setStudentClassrooms(Set<StudentClassroom> studentClassrooms) {
        this.studentClassrooms = studentClassrooms;
    }
}
