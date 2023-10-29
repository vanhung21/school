package com.school.SchoolManagerment.dto;

import com.school.SchoolManagerment.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private String password;
}