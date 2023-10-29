package com.school.SchoolManagerment.services.student;

import com.school.SchoolManagerment.dto.SingleStudentDto;
import com.school.SchoolManagerment.entities.Users;
import com.school.SchoolManagerment.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public SingleStudentDto getStudentById(Long id) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        SingleStudentDto student = new SingleStudentDto();
        optionalUsers.ifPresent(user -> student.setStudentDto(optionalUsers.get().getStudentDTO()));
        return student;
    }
}
