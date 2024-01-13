package com.fling.fllingbe.domain.bouquet.presentation;


import com.fling.fllingbe.domain.bouquet.application.BouquetService;
import com.fling.fllingbe.domain.bouquet.dto.CreateBouquetRequest;
import com.fling.fllingbe.domain.bouquet.dto.GetBouquetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BouquetController {
    private final BouquetService bouquetService;
    @PostMapping("/bouquet/{id}")
    public ResponseEntity<String> createBouquet(@PathVariable("id")UUID id, @RequestBody CreateBouquetRequest request) {
        String response = bouquetService.createFirstBouquet(id, request);
        return ResponseEntity.ok().body("꽃다발 생성에 성공하였습니다.");
    }
    @GetMapping("/bouquet")
    public ResponseEntity<GetBouquetResponse> getBouquet(@RequestHeader("Authorization") String token) {
        GetBouquetResponse getBouquetResponse = bouquetService.getBouquetResponse(token);
        return ResponseEntity.ok().body(getBouquetResponse);
    }

}
