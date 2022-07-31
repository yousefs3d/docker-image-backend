package com.school.service.mapper;

import com.school.domain.Student;
import com.school.service.dto.StudentDTO;
import com.school.service.vm.StudentVM;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface StudentMapper {


    List<StudentDTO> studentToStudentDTO(List<Student> students);

    Student studentVMToStudent(StudentVM studentVM);

    StudentDTO studentToStudentDTO(Student student);
}
