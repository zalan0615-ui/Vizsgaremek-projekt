package com.gymforge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//---------------------------------------------------------------------------------------------------------//
//---------------------------------------------------------------------------------------------------------//

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AdminResponse {
    private Integer id;
    private String username;
    private String email;
    private Boolean isActive;
}

//---------------------------------------------------------------------------------------------------------//