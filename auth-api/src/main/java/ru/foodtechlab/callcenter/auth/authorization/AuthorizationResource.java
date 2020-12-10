package ru.foodtechlab.callcenter.auth.authorization;

import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.commons.routes.BaseRoutes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.foodtechlab.callcenter.auth.authorization.request.PasswordAuthorizationRequest;
import ru.foodtechlab.callcenter.auth.authorization.request.RefreshTokenRequest;
import ru.foodtechlab.callcenter.auth.authorization.response.TokenPairResponse;

import java.util.concurrent.CompletableFuture;

@RestController
public interface AuthorizationResource {

    public final String RESOURCE = "/auth";

    @PostMapping(value = BaseRoutes.NOT_SECURE + BaseRoutes.API + BaseRoutes.V1 + RESOURCE + "/login/username")
    CompletableFuture<SuccessApiResponse<TokenPairResponse>> login(@RequestBody PasswordAuthorizationRequest request);

    @PostMapping(value = BaseRoutes.NOT_SECURE + BaseRoutes.API + BaseRoutes.V1 + RESOURCE + "/token/refresh")
    CompletableFuture<SuccessApiResponse<TokenPairResponse>> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest);

}
