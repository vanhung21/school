package com.school.SchoolManagerment.controller;

import com.school.SchoolManagerment.dto.SingleStudentDto;
import com.school.SchoolManagerment.dto.StudentLeaveDto;
import com.school.SchoolManagerment.services.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication.getName() == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }
    SingleStudentDto student = studentService.getStudentById(id);
    if (student == null) {
      return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.status(HttpStatus.OK).body(student);
  }

  @PostMapping("/leave")
  public ResponseEntity<?> leave(@RequestBody StudentLeaveDto studentLeaveDto) {
    StudentLeaveDto leave = studentService.apply(studentLeaveDto);
    if (leave == null) {
      return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(leave);
  }

  @GetMapping("/leave/{studentId}")
  public ResponseEntity<?> getAllLeave(@PathVariable Long studentId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication.getName() == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }
    List<StudentLeaveDto> student = studentService.getAllLeaveStudentById(studentId);
    if (student == null) {
      return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(student);
  }
}
