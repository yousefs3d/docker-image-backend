package com.school.service;


import com.school.domain.Student;
import com.school.exception.BusinessException;
import com.school.repository.StudentRepository;
import com.school.service.dto.ClassroomDTO;
import com.school.service.dto.StudentDTO;
import com.school.service.mapper.StudentMapper;
import com.school.service.vm.StudentVM;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final StudentClassroomService studentClassroomService;
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper,
                          StudentClassroomService studentClassroomService) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.studentClassroomService = studentClassroomService;
    }

    private void validateStudent(StudentVM studentVM){
        Optional<Student> student = studentRepository.findByEmail(studentVM.getEmail());
        if(student.isPresent() && !student.get().getId().equals(studentVM.getId())){
            throw new BusinessException("Email address: " + student.get().getEmail() + " already exist.");
        }
    }

    private void saveStudent(Student student){
         studentRepository.save(student);
    }

    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return studentMapper.studentToStudentDTO(students);
    }

    public List<ClassroomDTO> getStudentClassrooms(Long studentId){
        return studentClassroomService.getClassroomsByStudentId(studentId);
    }


    public StudentDTO getStudentById(Long id){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new BusinessException("ID "+ id + "Not Found"));
        return studentMapper.studentToStudentDTO(student);
    }

    public void addNewStudent(StudentVM studentVM){
        validateStudent(studentVM);
        Student student = studentMapper.studentVMToStudent(studentVM);
        studentClassroomService.addNewStudentToClassroom(student,studentVM.getClassroomIds());
        saveStudent(student);
//        return student.getId();
    }

    public void updateStudent(StudentVM studentVM){
        validateStudent(studentVM);
        studentRepository.findById(studentVM.getId()).orElseThrow(
                () -> new BusinessException("ID " + studentVM.getId() + "Not Found"));
         saveStudent(studentMapper.studentVMToStudent(studentVM));
    }

    public void deleteStudent(Long id){
        studentRepository.deleteClassById(id);
    }

}
