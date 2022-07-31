package com.school.rest;


import com.school.service.ClassroomService;
import com.school.service.vm.ClassroomVM;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/school/class")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllClasses(){
        return ResponseEntity.ok(classroomService.getAllClassrooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClassroom(@PathVariable Long id){
        return new ResponseEntity<>(classroomService.getClassroomById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewClass(@RequestBody ClassroomVM classroomVM){
        classroomService.addNewClass(classroomVM);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateClassroom(@RequestBody ClassroomVM classroomVM){
        classroomService.updateClassroom(classroomVM);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClassroom(@PathVariable Long id){
        classroomService.deleteClassroom(id);
        return new ResponseEntity<> (HttpStatus.OK);
    }
}
