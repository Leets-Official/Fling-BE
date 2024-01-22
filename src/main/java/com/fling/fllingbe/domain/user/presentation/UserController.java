package com.fling.fllingbe.domain.user.presentation;


import com.fling.fllingbe.domain.user.application.UserService;
import com.fling.fllingbe.domain.user.dto.*;
import com.fling.fllingbe.global.dto.ResponseDto;
import com.fling.fllingbe.global.jwt.presentation.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.fling.fllingbe.domain.user.presentation.constant.ResponseMessage.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseDto<UserResponse> login(@RequestBody UserRequest request) throws Exception {
        UserResponse response =  userService.login(request);
        return ResponseDto.of(OK.value(), SUCCESS_LOGIN.getMessage(), response);
    }

    @PostMapping(value = "/register")
    public ResponseDto<UserResponse> register(@RequestBody UserRequest request) throws Exception {
        UserResponse response =  userService.register(request);
        return ResponseDto.of(OK.value(), SUCCESS_REGISTER.getMessage(), response);
    }

    @PostMapping(value = "/refresh")
    public ResponseDto<JwtResponse> refresh(@RequestBody RefreshRequest request) throws Exception {
        JwtResponse response =  userService.tokenRefresh(request);
        return ResponseDto.of(OK.value(), SUCCESS_REFRESH.getMessage(), response);
    }

    @PostMapping(value = "/test-login")
    public ResponseDto<UserResponse> testLogin(@RequestBody TestUserRequest request) throws Exception {
        UserResponse response =  userService.testLogin(request);
        return ResponseDto.of(OK.value(), SUCCESS_LOGIN.getMessage(), response);
    }

    @PostMapping(value = "/test-register")
    public ResponseDto<UserResponse> testRegister(@RequestBody TestUserRequest request) throws Exception {
        UserResponse response =  userService.testRegister(request);
        return ResponseDto.of(OK.value(), SUCCESS_REGISTER.getMessage(), response);
    }

    @GetMapping(value = "/by-id/{id}")
    public ResponseDto<UserInfoResponse> findUserById(@PathVariable UUID id) throws Exception {
        UserInfoResponse response =  userService.findUserById(id);
        return ResponseDto.of(OK.value(), SUCCESS_READ.getMessage(), response);
    }
    @GetMapping(value = "/by-email/{email}")
    public ResponseDto<UserInfoResponse> findUserByEmail(@PathVariable String email) throws Exception {
        UserInfoResponse response = userService.findUserByEmail(email);
        return ResponseDto.of(OK.value(), SUCCESS_READ.getMessage(), response);
    }
    @GetMapping(value = "/by-token")
    public ResponseDto<UserInfoResponse> findUserByToken(Authentication authentication) throws Exception {
        UserInfoResponse response =  userService.findUserByEmail(authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_READ.getMessage(), response);
    }
    @PatchMapping(value = "/nickname")
    public ResponseDto setNickname(@RequestBody NicknameRequest request, Authentication authentication) throws Exception {
        userService.setNickname(request, authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_UPDATE.getMessage());
    }

    @PatchMapping(value = "/congratulatee")
    public ResponseDto setCongratulateeInfo(@RequestBody CongratulateeDto request, Authentication authentication) throws Exception {
        userService.setCongratulateeInfo(request, authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_UPDATE.getMessage());
    }

    @GetMapping(value = "/congratulatee/{id}")
    public ResponseDto<CongratulateeDto> getCongratulateeInfo(@PathVariable UUID id) throws Exception {
        CongratulateeDto response =  userService.getCongratulateeInfo(id);
        return ResponseDto.of(OK.value(), SUCCESS_READ.getMessage(), response);
    }

    @DeleteMapping
    public ResponseDto delUser(Authentication authentication) throws Exception {
        userService.delUser(authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_DELETE.getMessage());
    }
}
