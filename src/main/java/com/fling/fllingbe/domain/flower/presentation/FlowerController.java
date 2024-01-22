package com.fling.fllingbe.domain.flower.presentation;


import com.fling.fllingbe.domain.flower.application.FlowerService;
import com.fling.fllingbe.domain.flower.dto.FlowerRequest;
import com.fling.fllingbe.domain.flower.dto.GetLetter;
import com.fling.fllingbe.domain.flower.dto.ReceivedFlower;
import com.fling.fllingbe.domain.flower.dto.SentFlower;
import com.fling.fllingbe.domain.item.dto.GetItemById;
import com.fling.fllingbe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.fling.fllingbe.domain.flower.presentation.constant.ResponseMessage.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class FlowerController {
    final private FlowerService flowerService;
    @PostMapping(value = "/flower/{receiverId}")
    public ResponseDto<String> writeLetter(@RequestBody FlowerRequest flowerRequest, @PathVariable("receiverId") UUID receiverId, Authentication authentication) {
        String response = flowerService.writeLetter(flowerRequest, authentication.getName(), receiverId);
        return ResponseDto.of(OK.value(), SUCCESS_WRITE.getMessage(),response);
    }

    @GetMapping(value = "/letter-sent")
    public ResponseDto<List<SentFlower>> getSentFlower(Authentication authentication) {
        List<SentFlower> sentFlowerList = flowerService.getSentFlower(authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_READ_SENT_FLOWER.getMessage(),sentFlowerList);
    }

    @GetMapping(value = "/letter-received")
    public ResponseDto<List<ReceivedFlower>> getReceivedFlower(Authentication authentication) {
        List<ReceivedFlower> receivedFlowerList = flowerService.getReceivedFlower(authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_READ_RECEIVED_FLOWER.getMessage(),receivedFlowerList);
    }

    @GetMapping(value = "/flower")
    public ResponseDto<GetLetter> getLetter(@RequestParam(value = "id") Long request) {
        GetLetter getLetter = flowerService.getLetter(request);
        return ResponseDto.of(OK.value(), SUCCESS_READ_LETTER.getMessage(),getLetter);
    }
}
