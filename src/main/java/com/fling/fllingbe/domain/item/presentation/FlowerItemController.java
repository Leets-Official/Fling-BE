package com.fling.fllingbe.domain.item.presentation;

import com.fling.fllingbe.domain.item.application.FlowerItemService;
import com.fling.fllingbe.domain.item.dto.AddFlowerItemRequest;
import com.fling.fllingbe.domain.item.dto.FlowerItemResponse;
import com.fling.fllingbe.domain.item.dto.GetItemById;
import com.fling.fllingbe.domain.item.dto.GetItemResponse;
import com.fling.fllingbe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fling.fllingbe.domain.item.presentation.constant.ResponseMessage.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class FlowerItemController {
    private final FlowerItemService flowerItemService;

    @GetMapping(value = "/floweritem")
    public ResponseDto<List<FlowerItemResponse>> getFlowerItem(Authentication authentication) {
        List<FlowerItemResponse> flowerItems = flowerItemService.getFlowerItem(authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_READ_FLOWER.getMessage(),flowerItems);
    }

    @GetMapping("/floweritem-info")
    public ResponseDto<GetItemResponse> getFlowerItemInfo(@RequestBody GetItemById request) {
        GetItemResponse getItemResponse = flowerItemService.getFlowerItemInfo(request);
        return ResponseDto.of(OK.value(), SUCCESS_READ_FLOWER_INFO.getMessage(),getItemResponse);
    }

    @PostMapping("/addflower")
    public ResponseDto<String> addNewFlower(@RequestBody AddFlowerItemRequest request) {
        String response = flowerItemService.addNewFlowerItem(request);
        return ResponseDto.of(OK.value(), SUCCESS_ADD_FLOWER.getMessage(),response);
    }
}
