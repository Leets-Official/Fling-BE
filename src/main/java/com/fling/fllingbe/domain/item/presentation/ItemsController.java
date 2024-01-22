package com.fling.fllingbe.domain.item.presentation;


import com.fling.fllingbe.domain.item.application.ItemsService;
import com.fling.fllingbe.domain.item.dto.BouquetItemResponse;
import com.fling.fllingbe.domain.item.dto.GetItemsResponse;
import com.fling.fllingbe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fling.fllingbe.domain.item.presentation.constant.ResponseMessage.SUCCESS_READ_BOUQUET;
import static com.fling.fllingbe.domain.item.presentation.constant.ResponseMessage.SUCCESS_READ_ITEM;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class ItemsController {
    private final ItemsService itemsService;

    @GetMapping("/items")
    public ResponseDto<GetItemsResponse> getItems(Authentication authentication) {
        GetItemsResponse getItemsResponse = itemsService.getItems(authentication);
        return ResponseDto.of(OK.value(), SUCCESS_READ_ITEM.getMessage(),getItemsResponse);
    }

    @GetMapping("/bouquetitems")
    public ResponseDto<BouquetItemResponse> getBouquetItems() {
        BouquetItemResponse bouquetItemResponse = itemsService.getBouquetItemResponse();
        return ResponseDto.of(OK.value(), SUCCESS_READ_BOUQUET.getMessage(),bouquetItemResponse);
    }
}
