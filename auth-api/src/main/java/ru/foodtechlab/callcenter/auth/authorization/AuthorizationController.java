package ru.foodtechlab.callcenter.auth.authorization;

import com.rcore.domain.auth.authorization.usecases.ConfirmTwoFactorAuthorizationUseCase;
import com.rcore.domain.auth.authorization.usecases.InitTwoFactorAuthorizationUseCase;
import com.rcore.domain.auth.authorization.usecases.LogoutUseCase;
import com.rcore.domain.auth.authorization.usecases.PasswordAuthorizationUseCase;
import com.rcore.domain.auth.token.port.SessionTokenService;
import com.rcore.domain.auth.token.usecases.RefreshAccessTokenUseCase;
import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.rest.api.commons.response.OkApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.foodtechlab.callcenter.auth.authorization.mappers.TokenPairResponseMapper;
import ru.foodtechlab.callcenter.auth.authorization.request.InitTwoFactorAuthorizationRequest;
import ru.foodtechlab.callcenter.auth.authorization.request.PasswordAuthorizationRequest;
import ru.foodtechlab.callcenter.auth.authorization.request.RefreshTokenRequest;
import ru.foodtechlab.callcenter.auth.authorization.request.TwoFactorAuthorizationConfirmRequest;
import ru.foodtechlab.callcenter.auth.authorization.response.TokenPairResponse;
import ru.foodtechlab.callcenter.auth.config.security.CurrentUser;
import ru.foodtechlab.callcenter.auth.config.security.UserPrincipal;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
public class AuthorizationController implements AuthorizationResource {

    private final UseCaseExecutor useCaseExecutor;
    private final PasswordAuthorizationUseCase passwordAuthorizationUseCase;
    private final RefreshAccessTokenUseCase refreshAccessTokenUseCase;
    private final LogoutUseCase logoutUseCase;
    private final InitTwoFactorAuthorizationUseCase initTwoFactorAuthorizationUseCase;
    private final ConfirmTwoFactorAuthorizationUseCase confirmTwoFactorAuthorizationUseCase;

    private final TokenPairResponseMapper tokenPairResponseMapper;
    private final SessionTokenService sessionTokenService;

    @Override
    public CompletableFuture<SuccessApiResponse<TokenPairResponse>> passwordAuthorization(PasswordAuthorizationRequest request) {
        return useCaseExecutor.execute(
                passwordAuthorizationUseCase,
                request.toInputValues(),
                o -> SuccessApiResponse.of(tokenPairResponseMapper.map(o.getTokenPair()))
        );
    }

    @Override
    public CompletableFuture<SuccessApiResponse<TokenPairResponse>> refreshToken(RefreshTokenRequest request) {
        return useCaseExecutor.execute(
                refreshAccessTokenUseCase,
                request.toInputValues(),
                o -> SuccessApiResponse.of(tokenPairResponseMapper.map(o.getTokenPair()))
        );
    }

    @Override
    public CompletableFuture<OkApiResponse> initTwoFactorAuthorization(InitTwoFactorAuthorizationRequest request) {
        return useCaseExecutor.execute(
                initTwoFactorAuthorizationUseCase,
                request.toInputValues(60l),
                o -> OkApiResponse.of()
        );
    }

    @Override
    public CompletableFuture<SuccessApiResponse<TokenPairResponse>> confirmTwoFactorAuthorization(TwoFactorAuthorizationConfirmRequest request) {
        return useCaseExecutor.execute(
                confirmTwoFactorAuthorizationUseCase,
                request.toInputValues(),
                o -> SuccessApiResponse.of(tokenPairResponseMapper.map(o.getTokenPair()))
        );
    }

    @Override
    public CompletableFuture<OkApiResponse> logout() {
        return useCaseExecutor.execute(
                logoutUseCase,
                LogoutUseCase.InputValues.of(sessionTokenService.getSessionAccessToken()),
                o -> OkApiResponse.of()
        );
    }
}
