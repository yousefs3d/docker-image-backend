package com.school.rest;


import com.school.service.StudentService;
import com.school.service.vm.StudentVM;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/school/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id){
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @GetMapping("/classrooms/{studentId}")
    public ResponseEntity<?> getStudentClassrooms(@PathVariable Long studentId){
        return new ResponseEntity<>(studentService.getStudentClassrooms(studentId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewStudent(@RequestBody StudentVM studentVM){
        studentService.addNewStudent(studentVM);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody StudentVM studentVM){
        studentService.updateStudent(studentVM);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<> (HttpStatus.OK);
    }
}
