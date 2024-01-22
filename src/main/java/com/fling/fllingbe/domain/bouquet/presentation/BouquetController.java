package com.fling.fllingbe.domain.bouquet.presentation;


import com.fling.fllingbe.domain.bouquet.application.BouquetService;
import com.fling.fllingbe.domain.bouquet.dto.CreateBouquetRequest;
import com.fling.fllingbe.domain.bouquet.dto.GetBouquetResponse;
import com.fling.fllingbe.domain.bouquet.dto.UpdateBouquetRequest;
import com.fling.fllingbe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.fling.fllingbe.domain.bouquet.presentation.constant.ResponseMessage.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class BouquetController {
    private final BouquetService bouquetService;
    @PostMapping("/bouquet")
    public ResponseDto<String> createBouquet(Authentication authentication, @RequestBody CreateBouquetRequest request) {
        String response = bouquetService.createFirstBouquet(authentication, request);
        return ResponseDto.of(OK.value(), SUCCESS_CREATE.getMessage(),response);
    }

    @GetMapping("/bouquet/{id}")
    public ResponseDto<GetBouquetResponse> getBouquet(@PathVariable(value = "id") UUID userId) {
        GetBouquetResponse getBouquetResponse = bouquetService.getBouquetResponse(userId);
        return ResponseDto.of(OK.value(), SUCCESS_READ.getMessage(),getBouquetResponse);
    }

    @PatchMapping("/bouquet")
    public ResponseDto<String> updateBouquet(@RequestBody UpdateBouquetRequest request,Authentication authentication) {
        String response = bouquetService.updateBouquet(request, authentication);
        return ResponseDto.of(OK.value(), SUCCESS_UPDATE.getMessage(),response);
    }
}
