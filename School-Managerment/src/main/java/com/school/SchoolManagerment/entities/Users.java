package com.school.SchoolManagerment.entities;

import com.school.SchoolManagerment.dto.StudentDto;
import com.school.SchoolManagerment.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.ast.tree.expression.SqlTuple;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private  String name;
    private String email;
    private String password;
    private UserRole role;

    public StudentDto getStudentDTO() {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(id);
        studentDto.setName(name);
        studentDto.setEmail(email);
        return studentDto;
    }
}
