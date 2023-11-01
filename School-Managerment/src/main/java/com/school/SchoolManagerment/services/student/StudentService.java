package com.school.SchoolManagerment.services.student;

import com.school.SchoolManagerment.dto.SingleStudentDto;
import com.school.SchoolManagerment.dto.StudentLeaveDto;

import java.util.List;

public interface StudentService {
    SingleStudentDto getStudentById(Long id);

    StudentLeaveDto apply(StudentLeaveDto studentLeaveDto);

  List<StudentLeaveDto> getAllLeaveStudentById(Long studentId);
}
