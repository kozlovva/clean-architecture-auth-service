package ru.foodtechlab.callcenter.auth.authorization;

import com.rcore.rest.api.commons.response.OkApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.foodtechlab.callcenter.auth.authorization.request.InitTwoFactorAuthorizationRequest;
import ru.foodtechlab.callcenter.auth.authorization.request.PasswordAuthorizationRequest;
import ru.foodtechlab.callcenter.auth.authorization.request.RefreshTokenRequest;
import ru.foodtechlab.callcenter.auth.authorization.request.TwoFactorAuthorizationConfirmRequest;
import ru.foodtechlab.callcenter.auth.authorization.response.TokenPairResponse;

import java.util.concurrent.CompletableFuture;

@RestController
public interface AuthorizationResource {

    @PostMapping(value = Routes.PASSWORD_LOGIN)
    CompletableFuture<SuccessApiResponse<TokenPairResponse>> passwordAuthorization(@RequestBody PasswordAuthorizationRequest request);

    @PostMapping(value = Routes.TWO_FACTOR_INIT)
    CompletableFuture<OkApiResponse> initTwoFactorAuthorization(@RequestBody InitTwoFactorAuthorizationRequest request);

    @PostMapping(value = Routes.TWO_FACTOR_CONFIRM)
    CompletableFuture<SuccessApiResponse<TokenPairResponse>> confirmTwoFactorAuthorization(@RequestBody TwoFactorAuthorizationConfirmRequest request);

    @PostMapping(value = Routes.REFRESH)
    CompletableFuture<SuccessApiResponse<TokenPairResponse>> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest);

    @PostMapping(value = Routes.LOGOUT)
    CompletableFuture<OkApiResponse> logout();

}
