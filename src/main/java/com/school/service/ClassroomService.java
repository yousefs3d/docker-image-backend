package com.school.service;
import com.school.domain.Classroom;
import com.school.exception.BusinessException;
import com.school.repository.ClassroomRepository;
import com.school.service.dto.ClassroomDTO;
import com.school.service.mapper.ClassroomMapper;
import com.school.service.vm.ClassroomVM;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;

    public ClassroomService(ClassroomRepository classroomRepository, ClassroomMapper classroomMapper) {
        this.classroomRepository = classroomRepository;
        this.classroomMapper = classroomMapper;
    }

    private void saveClassroom(Classroom classroom){
         classroomRepository.save(classroom);
    }

    public List<ClassroomDTO> getAllClassrooms(){
        List<Classroom> myClassroom = classroomRepository.findAll();
        return classroomMapper.classroomsToClassroomDTOs(myClassroom);
    }

    public ClassroomDTO getClassroomById(Long id){
        Classroom classroom = classroomRepository.findById(id).orElseThrow(
                () -> new BusinessException("ID "+ id + "Not Found"));
        return classroomMapper.classroomToClassroomDTO(classroom);
    }

    public List<Classroom> getAllClassroomsByIds(List<Long> classroomIds){
        return classroomRepository.findAllById(classroomIds);
    }

    public void addNewClass(ClassroomVM classroomVM){
         saveClassroom(classroomMapper.classroomVMToClassroom(classroomVM));
    }

    public void updateClassroom(ClassroomVM classroomVM){
        classroomRepository.findById(classroomVM.getId()).orElseThrow(
                () -> new BusinessException("ID " + classroomVM.getId() + "Not Found"));
         saveClassroom(classroomMapper.classroomVMToClassroom(classroomVM));
    }

    public void deleteClassroom(Long id){
        classroomRepository.deleteClassById(id);
    }
}
