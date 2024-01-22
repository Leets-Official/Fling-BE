package com.fling.fllingbe.domain.user.application;

import com.fling.fllingbe.domain.coin.domain.Coin;
import com.fling.fllingbe.domain.coin.repository.CoinRepository;
import com.fling.fllingbe.domain.item.application.CardItemService;
import com.fling.fllingbe.domain.item.application.DecoItemService;
import com.fling.fllingbe.domain.item.application.FlowerItemService;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.dto.*;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import com.fling.fllingbe.global.jwt.JwtProvider;
import com.fling.fllingbe.global.jwt.presentation.JwtResponse;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Value("${is-using-refresh-token}")
    private boolean isUsingRefreshToken;

    private final UserRepository userRepository;
    private final CoinRepository coinRepository;
    private final FlowerItemService flowerItemService;
    private final DecoItemService decoItemService;
    private final CardItemService cardItemService;
    private final JwtProvider jwtProvider;

    public ResponseEntity<UserResponse> login(UserRequest request) throws Exception {
        String email = getEmail(request.getAccessToken());
        if (userRepository.existsByEmail(email)) {
            User user = userRepository.findByEmail(email).get();
            JwtResponse tokenDto = new JwtResponse(
                    jwtProvider.createAccessToken(user.getEmail()),
                    jwtProvider.createRefreshToken(user.getEmail())
            );
            UserResponse signResponse = UserResponse.builder()
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .token(tokenDto)
                    .build();
            return new ResponseEntity<>(signResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<UserResponse> register(UserRequest request) throws Exception {
        String email = getEmail(request.getAccessToken());
        if (userRepository.existsByEmail(email))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        User user = User.builder()
                .email(email)
                .nickname(request.getNickname())
                .build();
        Coin coin = Coin.builder()
                .user(user)
                .coin(1000)
                .build();
        userRepository.save(user);
        coinRepository.save(coin);
        flowerItemService.createDefaultFlowerItem(user);
        decoItemService.createDefaultDecoItem(user);
        cardItemService.createDefaultCardItem(user);

        JwtResponse tokenDto = new JwtResponse(
                jwtProvider.createAccessToken(user.getEmail()),
                jwtProvider.createRefreshToken(user.getEmail())
        );
        UserResponse signResponse = UserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .token(tokenDto)
                .build();
        return new ResponseEntity<>(signResponse, HttpStatus.OK);
    }

    public ResponseEntity<JwtResponse> tokenRefresh(RefreshRequest request) throws Exception {
        if (isUsingRefreshToken) {
            JwtResponse jwtResponse = jwtProvider.reissueToken(request);
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        }
        return null;
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
        if (hasEmail) {
            email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
        }

        System.out.println("id : " + id);
        System.out.println("email : " + email);
        br.close();
        return email;
    }


    public ResponseEntity<UserResponse> testLogin(TestUserRequest request) throws Exception {
        if (userRepository.existsByEmail(request.getEmail())) {
            User user = userRepository.findByEmail(request.getEmail()).get();
            JwtResponse tokenDto = new JwtResponse(
                    jwtProvider.createAccessToken(user.getEmail()),
                    isUsingRefreshToken
                            ?
                            jwtProvider.createRefreshToken(user.getEmail())
                            :
                            "No Refresh Token Provided"
            );
            UserResponse signResponse = UserResponse.builder()
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .token(tokenDto)
                    .build();
            return new ResponseEntity<>(signResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<UserResponse> testRegister(TestUserRequest request) throws Exception {
        if (userRepository.existsByEmail(request.getEmail()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .build();
        Coin coin = Coin.builder()
                .user(user)
                .coin(1000)
                .build();
        userRepository.save(user);
        coinRepository.save(coin);
        flowerItemService.createDefaultFlowerItem(user);
        decoItemService.createDefaultDecoItem(user);
        cardItemService.createDefaultCardItem(user);

        JwtResponse tokenDto = new JwtResponse(
                jwtProvider.createAccessToken(user.getEmail()),
                jwtProvider.createRefreshToken(user.getEmail())
        );
        UserResponse signResponse = UserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .token(tokenDto)
                .build();
        return new ResponseEntity<>(signResponse, HttpStatus.OK);
    }

    public ResponseEntity<UserInfoResponse> findUserById(UUID id) throws Exception {
        if (!userRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User user = userRepository.findById(id).get();
        Coin coin = coinRepository.findByUser(user).get();
        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .dDay(user.getDDay())
                .description(user.getDescription())
                .coin(coin.getCoin())
                .build();
        return new ResponseEntity<>(userInfoResponse, HttpStatus.OK);
    }

    public ResponseEntity<UserInfoResponse> findUserByEmail(String email) throws Exception {
        if (!userRepository.existsByEmail(email))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User user = userRepository.findByEmail(email).get();
        Coin coin = coinRepository.findByUser(user).get();
        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .dDay(user.getDDay())
                .description(user.getDescription())
                .coin(coin.getCoin())
                .build();
        return new ResponseEntity<>(userInfoResponse, HttpStatus.OK);
    }

    public ResponseEntity<Void> setNickname(NicknameRequest request, String email) throws Exception {
        if (!userRepository.existsByEmail(email))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User user = userRepository.findByEmail(email).get();
        user.setNickname(request.getNickname());
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> setCongratulateeInfo(CongratulateeDto request, String email) throws Exception {
        if (!userRepository.existsByEmail(email))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User user = userRepository.findByEmail(email).get();
        user.setDDay(request.getDDay());
        user.setDescription(request.getDescription());
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<CongratulateeDto> getCongratulateeInfo(UUID id) throws Exception {
        if (!userRepository.existsByUserId(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User user = userRepository.findByUserId(id).get();
        CongratulateeDto congratulateeDto = CongratulateeDto.builder()
                .dDay(user.getDDay())
                .description(user.getDescription())
                .build();
        return new ResponseEntity<>(congratulateeDto, HttpStatus.OK);
    }

    public ResponseEntity<Void> delUser(String email) throws Exception {
        if (!userRepository.existsByEmail(email))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userRepository.deleteByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
