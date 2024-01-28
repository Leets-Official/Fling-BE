package com.fling.fllingbe.domain.store.presentation;

import com.fling.fllingbe.domain.item.application.DecoItemService;
import com.fling.fllingbe.domain.store.application.StoreService;
import com.fling.fllingbe.domain.store.dto.CardPurchaseRequest;
import com.fling.fllingbe.domain.store.dto.DecoPurchaseRequest;
import com.fling.fllingbe.domain.store.dto.FlowerPurchaseRequest;
import com.fling.fllingbe.domain.store.dto.StoreResponse;
import com.fling.fllingbe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.fling.fllingbe.domain.store.presentation.constant.ResponseMessage.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/flower")
    public ResponseDto<Map<String, String>> purchaseFlower(Authentication authentication, @RequestBody FlowerPurchaseRequest request) {
        storeService.purchaseFlower(request, authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_BUY_FLOWER.getMessage());
    }

    @PostMapping("/deco")
    public ResponseDto<Map<String, String>> purchaseDeco(Authentication authentication, @RequestBody DecoPurchaseRequest request) {
        storeService.purchaseDeco(request, authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_BUY_DECO.getMessage());
    }

    @PostMapping("/card")
    public ResponseDto<Map<String, String>> purchaseCard(Authentication authentication, @RequestBody CardPurchaseRequest request) {
        storeService.purchaseCard(request, authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_BUY_CARD.getMessage());
    }

    @GetMapping
    public ResponseDto<StoreResponse> getStoreItems(Authentication authentication) {
        StoreResponse storeResponse = storeService.getStoreItems(authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_READ.getMessage(),storeResponse);
    }
}