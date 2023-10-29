package com.school.SchoolManagerment.controller;

import com.school.SchoolManagerment.dto.AuthenticationRequest;
import com.school.SchoolManagerment.dto.AuthenticationResponse;
import com.school.SchoolManagerment.entities.Users;
import com.school.SchoolManagerment.repositories.UsersRepository;
import com.school.SchoolManagerment.services.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsersRepository usersRepository;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization ";

    @PostMapping("/authentication")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password");
        }catch (DisabledException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created");
            System.out.println("Not found");
            return;
        }
        final UserDetails user = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        Optional<Users> optionalUsers = usersRepository.findFirstByEmail(user.getUsername());
        final String jwt = jwtUtil.generateToken(user.getUsername());
        if(optionalUsers.isPresent()) {
            response.getWriter().write(new JSONObject()
                    .put("userId", optionalUsers.get().getId())
                    .put("role", optionalUsers.get().getRole())
                    .put("token",jwt)
                    .toString());
        }

        response.setHeader("Access-Control-Expose-Headers","Authorization");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, X-Pingother, Origin, X-Requested-With, Content-Type, Accept, X-Custome-Header");
        response.setHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
    }
}
