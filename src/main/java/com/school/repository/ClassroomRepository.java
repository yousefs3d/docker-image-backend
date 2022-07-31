package com.school.repository;

import com.school.domain.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long>  {

    void deleteClassById(Long id);
}
