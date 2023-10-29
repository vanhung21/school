package com.school.SchoolManagerment.repositories;

import com.school.SchoolManagerment.dto.StudentDto;
import com.school.SchoolManagerment.entities.Users;
import com.school.SchoolManagerment.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByRole(UserRole userRole);

    Optional<Users> findFirstByEmail(String email);

    List<Users> findAllByRole(UserRole userRole);
}
