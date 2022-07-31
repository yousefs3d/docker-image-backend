package com.school.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "class")
public class Classroom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String room;
    private String subject;
    private String building;
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentClassroom> studentClassrooms = new HashSet<>();

    public Classroom() {
    }

    public Long getId() {
        return id;
    }

    public String getRoom() {
        return room;
    }

    public String getSubject() {
        return subject;
    }

    public String getBuilding() {
        return building;
    }

    public Set<StudentClassroom> getStudentClassrooms() {
        return studentClassrooms;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setStudentClassrooms(Set<StudentClassroom> studentClassrooms) {
        this.studentClassrooms = studentClassrooms;
    }
}
