package com.fling.fllingbe.domain.item.presentation;

import com.fling.fllingbe.domain.item.application.DecoItemService;
import com.fling.fllingbe.domain.item.dto.DecoItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DecoItemController {
    final private DecoItemService decoItemService;

    @GetMapping(value = "/deco-item")
    public ResponseEntity<List<DecoItemResponse>> getDecoItem(@PathVariable UUID id) {
        List<DecoItemResponse> decoItemResponses = decoItemService.getDecoItem(id);
        return ResponseEntity.ok().body(decoItemResponses);
    }
}
