package com.fling.fllingbe.domain.bouquet.presentation;


import com.fling.fllingbe.domain.bouquet.application.BouquetService;
import com.fling.fllingbe.domain.bouquet.dto.CreateBouquetRequest;
import com.fling.fllingbe.domain.bouquet.dto.GetBouquetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BouquetController {
    private final BouquetService bouquetService;
    @PostMapping("/bouquet")
    public ResponseEntity<String> createBouquet(Authentication authentication, @RequestBody CreateBouquetRequest request) {
        String response = bouquetService.createFirstBouquet(authentication, request);
        return ResponseEntity.ok().body("꽃다발 생성에 성공하였습니다.");
    }
    @GetMapping("/bouquet")
    public ResponseEntity<GetBouquetResponse> getBouquet(Authentication authentication) {
        GetBouquetResponse getBouquetResponse = bouquetService.getBouquetResponse(authentication);
        return ResponseEntity.ok().body(getBouquetResponse);
    }

}
