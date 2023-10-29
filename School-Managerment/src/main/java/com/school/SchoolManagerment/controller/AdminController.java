package com.school.SchoolManagerment.controller;

import com.school.SchoolManagerment.dto.SingleStudentDto;
import com.school.SchoolManagerment.dto.StudentDto;
import com.school.SchoolManagerment.services.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudent(){

        List<StudentDto> allStudent =  adminService.getAllStudent();
        return ResponseEntity.status(HttpStatus.OK).body(allStudent);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        SingleStudentDto student = adminService.getStudentById(id);
        if (student == null) {
            return  new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        StudentDto student = adminService.addStudent(studentDto);
        if(student == null){
            return  new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PostMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        StudentDto student = adminService.updateStudent(id, studentDto);
        if (student == null) {
            return  new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        adminService.deleteStudent(id);
        return ResponseEntity.ok().body("OK");
    }

}
