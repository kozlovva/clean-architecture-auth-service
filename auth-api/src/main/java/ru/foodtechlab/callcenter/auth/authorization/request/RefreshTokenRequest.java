package ru.foodtechlab.callcenter.auth.authorization.request;

import com.rcore.domain.auth.token.usecases.RefreshAccessTokenUseCase;
import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;

    public RefreshAccessTokenUseCase.InputValues toInputValues() {
        return RefreshAccessTokenUseCase.InputValues.of(refreshToken);
    }
}
