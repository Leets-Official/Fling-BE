package com.fling.fllingbe.domain.item.presentation;


import com.fling.fllingbe.domain.item.application.CardItemService;
import com.fling.fllingbe.domain.item.dto.*;
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
public class CardItemController {
    final private CardItemService cardItemService;

    @GetMapping(value = "/carditem")
    public ResponseDto<List<CardItemResponse>> getCardItem(Authentication authentication) {
        List<CardItemResponse> cardItems = cardItemService.getCardItem(authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_READ_CARD.getMessage(),cardItems);
    }

    @GetMapping(value = "/carditem-info")
    public ResponseDto<GetItemResponse> getCardItemInfo(@RequestBody GetItemById getItemById) {
        GetItemResponse getItemResponse = cardItemService.getCardItemInfo(getItemById);
        return ResponseDto.of(OK.value(), SUCCESS_READ_CARD_INFO.getMessage(),getItemResponse);
    }

    @PostMapping("/addcard")
    public ResponseDto<String> addNewCardItem(@RequestBody AddCardItemRequest request) {
        String response = cardItemService.addNewCardItem(request);
        return ResponseDto.of(OK.value(), SUCCESS_ADD_CARD.getMessage(),response);
    }
}
