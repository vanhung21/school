package com.school.SchoolManagerment.services.jwt;

import com.school.SchoolManagerment.entities.Users;
import com.school.SchoolManagerment.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> usersOptional = usersRepository.findFirstByEmail(email);
        if(usersOptional.isEmpty()) throw new UsernameNotFoundException("username not found", null);
        return new org.springframework.security.core.userdetails.User(usersOptional.get().getEmail(), usersOptional.get().getPassword(), new ArrayList<>());
    }
}
