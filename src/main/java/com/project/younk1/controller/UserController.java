package com.project.younk1.controller;

import com.project.younk1.dto.request.UserRequest;
import com.project.younk1.dto.response.UserResponse;
import com.project.younk1.entity.User;
import com.project.younk1.service.UserService;
import com.project.younk1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest user) {

        System.out.println(user);

        User newUser = userService.addUser(user);

        return new ResponseEntity<>(userService.response(newUser, ""), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> findUser(@RequestBody UserRequest userRequest) {

        System.out.println(userRequest);

        User user = userService.findUser(userRequest);

        String accessToken = jwtUtil.createToken(user.getId(), user.getEmail());

        return new ResponseEntity<>(userService.response(user, accessToken), HttpStatus.OK);
    }

}
