package com.school.service;


import com.school.domain.Classroom;
import com.school.domain.Student;
import com.school.domain.StudentClassroom;
import com.school.exception.BusinessException;
import com.school.repository.StudentClassroomRepository;
import com.school.service.dto.ClassroomDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class StudentClassroomService {

    private final StudentClassroomRepository studentClassroomRepository;
    private final ClassroomService classroomService;

    public StudentClassroomService(StudentClassroomRepository studentClassroomRepository, ClassroomService classroomService) {
        this.studentClassroomRepository = studentClassroomRepository;
        this.classroomService = classroomService;
    }

    private void saveStudentClassroom(List<StudentClassroom> studentClassrooms){
        studentClassroomRepository.saveAll(studentClassrooms);
    }

    public void addNewStudentToClassroom(Student student, List<Long>classroomIds){
        List<Classroom> classrooms = classroomService.getAllClassroomsByIds(classroomIds);
        if(!classrooms.isEmpty()){
            List<StudentClassroom> studentClassrooms = classrooms.stream().map(classroom -> {
                StudentClassroom studentClassroom = new StudentClassroom();
                studentClassroom.setStudent(student);
                studentClassroom.setClassroom(classroom);
                studentClassroom.setActive(true);
                return studentClassroom;
            }).collect(Collectors.toList());
            saveStudentClassroom(studentClassrooms);
        }else{
            throw new BusinessException("One or more classrooms don't exist");
        }
    }

    public List<ClassroomDTO> getClassroomsByStudentId(Long studentId){
        List<StudentClassroom> studentClassrooms = studentClassroomRepository.findAllClassroomByStudentId(studentId);
        return studentClassrooms.stream().map(studentClassroom -> {
            ClassroomDTO classroomDTO = new ClassroomDTO();
            classroomDTO.setId(studentClassroom.getClassroom().getId());
            classroomDTO.setRoom(studentClassroom.getClassroom().getRoom());
            classroomDTO.setSubject(studentClassroom.getClassroom().getSubject());
            classroomDTO.setBuilding(studentClassroom.getClassroom().getBuilding());
            return classroomDTO;
        }).collect(Collectors.toList());
    }



}
