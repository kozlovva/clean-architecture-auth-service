package ru.foodtechlab.callcenter.auth.authorization.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenPairResponse {
    private String accessToken;
    private String refreshToken;

}
