package com.school.SchoolManagerment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.SchoolManagerment.dto.StudentLeaveDto;
import com.school.SchoolManagerment.enums.StudentLeaveStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Getter
@Setter
public class StudentLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String body;
    private String date;
    private StudentLeaveStatus studentStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Users users;

    public StudentLeaveDto getStudentLeaveDto(){
        StudentLeaveDto studentLeaveDto = new StudentLeaveDto();
        studentLeaveDto.setId(id);
        studentLeaveDto.setSubject(subject);
        studentLeaveDto.setBody(body);
        studentLeaveDto.setDate(date);
        studentLeaveDto.setStudentStatus(studentStatus);
        studentLeaveDto.setUsersId(users.getId());
        return studentLeaveDto;
    }
}
