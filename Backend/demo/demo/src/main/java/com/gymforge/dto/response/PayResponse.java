package com.gymforge.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

//----------------------------------------------------------------------------------------------//

@Getter
@Builder
public class PayResponse {
    private Integer id;
    private Integer userId;
    private Integer membershipId;
    private BigDecimal price;
    private String status;
    private Boolean emailSent;
}

//----------------------------------------------------------------------------------------------//