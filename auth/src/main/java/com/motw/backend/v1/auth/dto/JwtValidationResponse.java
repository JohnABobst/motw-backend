package com.motw.backend.v1.auth.dto;

import com.motw.backend.v1.auth.models.Role;
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

}
