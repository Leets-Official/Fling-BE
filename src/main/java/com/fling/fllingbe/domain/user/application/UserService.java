package com.fling.fllingbe.domain.user.application;

import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.dto.TestUserRequest;
import com.fling.fllingbe.domain.user.dto.UserRequest;
import com.fling.fllingbe.domain.user.dto.UserResponse;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import com.fling.fllingbe.global.jwt.JwtProvider;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;


    public ResponseEntity<UserResponse> login(UserRequest request) throws Exception {
        String email = getEmail(request.getAccessToken());
        if (userRepository.existsByEmail(email)){
            User user = userRepository.findByEmail(email).get();
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
        String email = getEmail(request.getAccessToken());
        if (userRepository.existsByEmail(email))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        User user = User.builder()
                .email(email)
                .nickname(request.getNickname())
                .build();
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public String getEmail(String token) throws Exception {
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        URL url = new URL(reqURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = conn.getResponseCode();
        System.out.println("responseCode : " + responseCode);

        //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        String result = "";

        while ((line = br.readLine()) != null) {
            result += line;
        }
        System.out.println("response body : " + result);

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);

        int id = element.getAsJsonObject().get("id").getAsInt();
        boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
        String email = "";
        if(hasEmail){
            email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
        }

        System.out.println("id : " + id);
        System.out.println("email : " + email);
        br.close();
        return email;
    }


    public ResponseEntity<UserResponse> testLogin(TestUserRequest request) throws Exception {
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

    public ResponseEntity<Void> testRegister(TestUserRequest request) throws Exception {
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
