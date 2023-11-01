package com.school.SchoolManagerment.dto;

import com.school.SchoolManagerment.enums.StudentLeaveStatus;
import lombok.Data;

import java.util.Date;

@Data
public class StudentLeaveDto {
  private Long id;
  private String subject;
  private String body;
  private String date;
  private StudentLeaveStatus studentStatus;
  private Long usersId;
}
