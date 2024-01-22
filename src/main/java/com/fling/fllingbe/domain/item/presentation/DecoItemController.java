package com.fling.fllingbe.domain.item.presentation;

import com.fling.fllingbe.domain.item.application.DecoItemService;
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
public class DecoItemController {
    final private DecoItemService decoItemService;

    @GetMapping(value = "/decoitem")
    public ResponseDto<List<DecoItemResponse>> getDecoItem(Authentication authentication) {
        List<DecoItemResponse> decoItemResponses = decoItemService.getDecoItem(authentication.getName());
        return ResponseDto.of(OK.value(), SUCCESS_READ_DECO.getMessage(),decoItemResponses);
    }

    @GetMapping(value = "/decoitem-info")
    public ResponseDto<GetItemResponse> getDecoItemInfo(@RequestBody GetItemById request) {
        GetItemResponse getItemResponse = decoItemService.getDecoItemInfo(request);
        return ResponseDto.of(OK.value(), SUCCESS_READ_DECO_INFO.getMessage(),getItemResponse);
    }

    @PostMapping("/adddeco")
    public ResponseDto<String> addNewDecoItem(@RequestBody AddDecoItemRequest request) {
        String response = decoItemService.addNewDecoItem(request);
        return ResponseDto.of(OK.value(), SUCCESS_ADD_DECO.getMessage(),response);
    }
}
