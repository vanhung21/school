package com.school.SchoolManagerment.services.admin;

import com.school.SchoolManagerment.dto.SingleStudentDto;
import com.school.SchoolManagerment.dto.StudentDto;
import com.school.SchoolManagerment.dto.StudentLeaveDto;

import java.util.List;

public interface AdminService {
    StudentDto addStudent(StudentDto studentDto);

    List<StudentDto> getAllStudent();

    void deleteStudent(Long id);

    SingleStudentDto getStudentById(Long id);

    StudentDto updateStudent(Long id, StudentDto studentDto);

  List<StudentLeaveDto> getAllLeave();

  StudentLeaveDto updateStatus(Long id, String status);
}
