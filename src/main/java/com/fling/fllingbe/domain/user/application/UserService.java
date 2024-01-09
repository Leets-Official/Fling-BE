package com.fling.fllingbe.domain.user.application;

import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.dto.UserRequest;
import com.fling.fllingbe.domain.user.dto.UserResponse;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import com.fling.fllingbe.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<UserResponse> login(UserRequest request) throws Exception {
        if (userRepository.existsByEmail(request.getEmail())){
            User user = userRepository.findByEmail(request.getEmail()).get();

            UserResponse signResponse= UserResponse.builder()
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .accessToken(jwtProvider.generateToken(user.getUserId(), user.getEmail(), false))
//                    .refreshToken()
                    .build();
            return new ResponseEntity<>(signResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> register(UserRequest request) throws Exception {
        if (userRepository.existsByEmail(request.getEmail()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .build();
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
