package com.fling.fllingbe.domain.user.presentation;


import com.fling.fllingbe.domain.user.application.UserService;
import com.fling.fllingbe.domain.user.dto.TestUserRequest;
import com.fling.fllingbe.domain.user.dto.UserRequest;
import com.fling.fllingbe.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest request) throws Exception {
        return userService.login(request);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@RequestBody UserRequest request) throws Exception {
        return userService.register(request);
    }

    @PostMapping(value = "/test-login")
    public ResponseEntity<UserResponse> testLogin(@RequestBody TestUserRequest request) throws Exception {
        return userService.testLogin(request);
    }

    @PostMapping(value = "/test-register")
    public ResponseEntity<Void> testRegister(@RequestBody TestUserRequest request) throws Exception {
        return userService.testRegister(request);
    }
}