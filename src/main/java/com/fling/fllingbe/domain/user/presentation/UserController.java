package com.fling.fllingbe.domain.user.presentation;


import com.fling.fllingbe.domain.user.application.UserService;
import com.fling.fllingbe.domain.user.dto.*;
import com.fling.fllingbe.global.jwt.presentation.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest request) throws Exception {
        return userService.login(request);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@RequestBody UserRequest request) throws Exception {
        return userService.register(request);
    }

    @GetMapping(value = "/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestBody RefreshRequest request) throws Exception {
        return userService.tokenRefresh(request);
    }

    @GetMapping(value = "/test-login")
    public ResponseEntity<UserResponse> testLogin(@RequestBody TestUserRequest request) throws Exception {
        return userService.testLogin(request);
    }

    @PostMapping(value = "/test-register")
    public ResponseEntity<Void> testRegister(@RequestBody TestUserRequest request) throws Exception {
        return userService.testRegister(request);
    }

    @PatchMapping(value = "/nickname")
    public ResponseEntity<Void> setNickname(@RequestBody NicknameRequest request, Authentication authentication) throws Exception {
        return userService.setNickname(request, authentication.getName());
    }

    @PatchMapping(value = "/congratulatee")
    public ResponseEntity<Void> setCongratulateeInfo(@RequestBody CongratulateeDto request, Authentication authentication) throws Exception {
        return userService.setCongratulateeInfo(request, authentication.getName());
    }

    @GetMapping(value = "/congratulatee/{id}")
    public ResponseEntity<CongratulateeDto> getCongratulateeInfo(@PathVariable UUID id) throws Exception {
        return userService.getCongratulateeInfo(id);
    }

    @DeleteMapping
    public ResponseEntity<Void> delUser(Authentication authentication) throws Exception {
        return userService.delUser(authentication.getName());
    }
}