package com.school.SchoolManagerment.services.student;

import com.school.SchoolManagerment.dto.SingleStudentDto;
import com.school.SchoolManagerment.dto.StudentLeaveDto;
import com.school.SchoolManagerment.entities.StudentLeave;
import com.school.SchoolManagerment.entities.Users;
import com.school.SchoolManagerment.enums.StudentLeaveStatus;
import com.school.SchoolManagerment.repositories.StudentLeaveRepository;
import com.school.SchoolManagerment.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentLeaveRepository studentLeaveRepository;

    @Override
    public SingleStudentDto getStudentById(Long id) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        SingleStudentDto student = new SingleStudentDto();
        optionalUsers.ifPresent(user -> student.setStudentDto(optionalUsers.get().getStudentDTO()));
        return student;
    }

  @Override
  public StudentLeaveDto apply(StudentLeaveDto studentLeaveDto) {
      Optional<Users> optionalUsers = usersRepository.findById((studentLeaveDto.getUsersId()));
      if(optionalUsers.isPresent()){
        StudentLeave studentLeave = new StudentLeave();
        studentLeave.setSubject(studentLeaveDto.getSubject());
        studentLeave.setBody(studentLeaveDto.getBody());
        studentLeave.setDate(studentLeaveDto.getDate());
        studentLeave.setStudentStatus(StudentLeaveStatus.pending);
        studentLeave.setUsers(optionalUsers.get());
        StudentLeave submit = studentLeaveRepository.save(studentLeave);
        StudentLeaveDto student = new StudentLeaveDto();
        student.setId(submit.getId());
        return student;
      }
      return null;
  }

  @Override
  public List<StudentLeaveDto> getAllLeaveStudentById(Long studentId) {
    return studentLeaveRepository.findAllByUsersId(studentId).stream()
      .map(StudentLeave::getStudentLeaveDto)
      .collect(Collectors.toList());
  }
}
