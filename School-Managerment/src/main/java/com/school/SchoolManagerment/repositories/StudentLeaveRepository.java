package com.school.SchoolManagerment.repositories;

import com.school.SchoolManagerment.entities.StudentLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentLeaveRepository extends JpaRepository<StudentLeave, Long> {

  List<StudentLeave> findAllByUsersId(Long studentId);
}
