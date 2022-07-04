package com.project.younk1.service;

import com.project.younk1.dto.request.UserRequest;
import com.project.younk1.entity.User;
import com.project.younk1.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void addUser() {

        String email = "admin@example.com";
        UserRequest user = UserRequest.builder().email(email).password("admin1234").build();

        given(userRepository.save(any())).willReturn(user);

        User addUser = userService.addUser(user);

        assertThat(user.getEmail()).isEqualTo(email);
    }
}