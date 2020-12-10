package ru.foodtechlab.callcenter.auth.authorization.request;

import com.rcore.domain.auth.authorization.usecases.PasswordAuthorizationUseCase;
import lombok.Data;

@Data
public class PasswordAuthorizationRequest {
    private String username;
    private String password;

    public PasswordAuthorizationUseCase.InputValues toInputValues() {
        return PasswordAuthorizationUseCase.InputValues.of(username, password);
    }
}
