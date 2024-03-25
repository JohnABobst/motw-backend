package com.motw.keeper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Class to return a response as to whether or not the token passed is valid.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtValidationResponse {

    boolean isValid;

//    TODO Add more fields that may be needed by serices like user role or email.
}
