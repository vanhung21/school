package com.school.SchoolManagerment.services.student;

import com.school.SchoolManagerment.dto.SingleStudentDto;

public interface StudentService {
    SingleStudentDto getStudentById(Long id);
}
