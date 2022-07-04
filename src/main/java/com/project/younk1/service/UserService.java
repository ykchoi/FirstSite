package com.project.younk1.service;

import com.project.younk1.dto.ErrorCode;
import com.project.younk1.dto.request.UserRequest;
import com.project.younk1.dto.response.UserResponse;
import com.project.younk1.entity.User;
import com.project.younk1.exception.EmailDuplicateException;
import com.project.younk1.exception.EmailNotExistException;
import com.project.younk1.exception.PasswordWrongException;
import com.project.younk1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(UserRequest userRequest) {

        if(userRepository.existsByEmail(userRequest.getEmail())) {
            throw new EmailDuplicateException("duplicate email", ErrorCode.EMAIL_DUPLICATE);
        }
        else {
            System.out.printf("Create new user : " + userRequest);

            String encodedPassword = passwordEncoder.encode(userRequest.getPassword());

            User user = User.builder()
                    .email(userRequest.getEmail())
                    .password(encodedPassword)
                    .build();

            User newUser = userRepository.save(user);
            return newUser;
        }
    }

    public User findUser(UserRequest user) {

        User findUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new EmailNotExistException("email not exist", ErrorCode.EMAIL_NOT_EXIST));

        System.out.println("findUser : " + findUser);

        if(!passwordEncoder.matches(user.getPassword(), findUser.getPassword())) {
            System.out.println("Password wrong!");
            throw new PasswordWrongException("password wrong", ErrorCode.PASSWORD_WRONG);
        }

        System.out.println("Password matched!");

        return findUser;
    }

    public UserResponse response(User user, String token) {

        return UserResponse.builder()
                .email(user.getEmail())
                .token(token)
                .build();
    }

}
