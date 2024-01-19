package com.fling.fllingbe.domain.store.presentation;

import com.fling.fllingbe.domain.item.application.DecoItemService;
import com.fling.fllingbe.domain.store.application.StoreService;
import com.fling.fllingbe.domain.store.dto.CardPurchaseRequest;
import com.fling.fllingbe.domain.store.dto.DecoPurchaseRequest;
import com.fling.fllingbe.domain.store.dto.FlowerPurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final DecoItemService decoItemService;

    @PostMapping("/flower")
    public ResponseEntity<Map<String, String>> purchaseFlower(Authentication authentication, @RequestBody FlowerPurchaseRequest request) {
        storeService.purchaseFlower(request, authentication.getName());
        return new ResponseEntity<>(Map.of("message", "꽃 구매를 성공했습니다."), HttpStatus.OK);
    }

    @PostMapping("/deco")
    public ResponseEntity<Map<String, String>> purchaseDeco(Authentication authentication, @RequestBody DecoPurchaseRequest request) {
        storeService.purchaseDeco(request, authentication.getName());
        return new ResponseEntity<>(Map.of("message", "데코 구매를 성공했습니다."), HttpStatus.OK);
    }

    @PostMapping("/card")
    public ResponseEntity<Map<String, String>> purchaseCard(Authentication authentication, @RequestBody CardPurchaseRequest request) {
        storeService.purchaseCard(request, authentication.getName());
        return new ResponseEntity<>(Map.of("message", "편지지 구매를 성공했습니다."), HttpStatus.OK);
    }
}
