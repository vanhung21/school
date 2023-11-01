package com.school.SchoolManagerment.services.admin;

import com.school.SchoolManagerment.dto.SingleStudentDto;
import com.school.SchoolManagerment.dto.StudentDto;
import com.school.SchoolManagerment.dto.StudentLeaveDto;
import com.school.SchoolManagerment.entities.StudentLeave;
import com.school.SchoolManagerment.entities.Users;
import com.school.SchoolManagerment.enums.StudentLeaveStatus;
import com.school.SchoolManagerment.enums.UserRole;
import com.school.SchoolManagerment.repositories.StudentLeaveRepository;
import com.school.SchoolManagerment.repositories.UsersRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentLeaveRepository studentLeaveRepository;

    @PostConstruct
    public void createAdminAccount() {
        Users adminAccount = usersRepository.findByRole(UserRole.ADMIN);
        if (adminAccount == null) {
            Users admin = new Users();
            admin.setName("admin");
            admin.setEmail("admin@admin.com");
            admin.setRole(UserRole.ADMIN);
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            usersRepository.save(admin);
        }
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        Optional<Users> optionalUsers = usersRepository.findFirstByEmail(studentDto.getEmail());
        if (optionalUsers.isEmpty()) {
            Users users = new Users();
            BeanUtils.copyProperties(studentDto, users);
            users.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
            users.setRole(UserRole.STUDENT);
            Users createUser = usersRepository.save(users);
            StudentDto createStudent = new StudentDto();
            createStudent.setId(createUser.getId());
            createStudent.setName(createUser.getEmail());
            return createStudent;
        }

        return null;
    }

    @Override
    public SingleStudentDto getStudentById(Long id) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        SingleStudentDto student = new SingleStudentDto();
        optionalUsers.ifPresent(user -> student.setStudentDto(optionalUsers.get().getStudentDTO()));
        return student;
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            users.setName(studentDto.getName());
            users.setEmail(studentDto.getEmail());
            Users updateStudent = usersRepository.save(users);
            StudentDto updateStudentDto = new StudentDto();
            updateStudentDto.setId(updateStudent.getId());
            return updateStudentDto;
        }

        return null;
    }

  @Override
  public List<StudentLeaveDto> getAllLeave() {
    return studentLeaveRepository.findAll().stream()
      .map(StudentLeave::getStudentLeaveDto)
      .collect(Collectors.toList());
  }

  @Override
  public StudentLeaveDto updateStatus(Long id, String status) {
      Optional<StudentLeave> optionalStudentLeave = studentLeaveRepository.findById(id);
      if (optionalStudentLeave.isPresent()) {
          StudentLeave studentLeave = optionalStudentLeave.get();
          if(status.equalsIgnoreCase("approved")){
            studentLeave.setStudentStatus(StudentLeaveStatus.approved);
          } else if(status.equalsIgnoreCase("disapproved")){
            studentLeave.setStudentStatus(StudentLeaveStatus.disapproved);
          }
          StudentLeave updateStudentLeave = studentLeaveRepository.save(studentLeave);
          StudentLeaveDto updateStudentLeaveDto = new StudentLeaveDto();
          updateStudentLeaveDto.setId(updateStudentLeave.getId());
          updateStudentLeaveDto.setStudentStatus(updateStudentLeave.getStudentStatus());
          return updateStudentLeaveDto;
      }
    return null;
  }

  @Override
    public List<StudentDto> getAllStudent() {
        return usersRepository.findAllByRole(UserRole.STUDENT).stream()
                .map(Users::getStudentDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteStudent(Long id) {
        usersRepository.deleteById(id);
    }

}
