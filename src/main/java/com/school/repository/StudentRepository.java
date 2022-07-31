package com.school.repository;

import com.school.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    void deleteClassById(Long id);
    Optional<Student> findByEmail(String email);
}
