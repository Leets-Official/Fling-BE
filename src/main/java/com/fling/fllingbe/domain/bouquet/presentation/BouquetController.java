package com.fling.fllingbe.domain.bouquet.presentation;


import com.fling.fllingbe.domain.bouquet.application.BouquetService;
import com.fling.fllingbe.domain.bouquet.dto.CreateBouquetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
