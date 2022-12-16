package com.example.PostgresDb.Repository;

import com.example.PostgresDb.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
