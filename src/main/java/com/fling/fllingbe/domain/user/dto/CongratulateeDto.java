package com.fling.fllingbe.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CongratulateeDto {
    private String dDay;
    private String description;
}
