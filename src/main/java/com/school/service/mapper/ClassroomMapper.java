package com.school.service.mapper;

import com.school.domain.Classroom;
import com.school.service.dto.ClassroomDTO;
import com.school.service.vm.ClassroomVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

    ClassroomMapper INSTANCE = Mappers.getMapper( ClassroomMapper.class );

    List<ClassroomDTO> classroomsToClassroomDTOs(List<Classroom> classrooms);

    Classroom classroomVMToClassroom (ClassroomVM classroomVM);

    ClassroomDTO classroomToClassroomDTO(Classroom classroom);

}
