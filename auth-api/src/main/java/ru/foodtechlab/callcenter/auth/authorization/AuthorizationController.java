package ru.foodtechlab.callcenter.auth.authorization;

import com.rcore.domain.auth.authorization.usecases.PasswordAuthorizationUseCase;
import com.rcore.domain.auth.token.usecases.RefreshAccessTokenUseCase;
import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.foodtechlab.callcenter.auth.authorization.mappers.TokenPairResponseMapper;
import ru.foodtechlab.callcenter.auth.authorization.request.PasswordAuthorizationRequest;
import ru.foodtechlab.callcenter.auth.authorization.request.RefreshTokenRequest;
import ru.foodtechlab.callcenter.auth.authorization.response.TokenPairResponse;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
public class AuthorizationController implements AuthorizationResource {

    private final UseCaseExecutor useCaseExecutor;
    private final PasswordAuthorizationUseCase passwordAuthorizationUseCase;
    private final RefreshAccessTokenUseCase refreshAccessTokenUseCase;

    private final TokenPairResponseMapper tokenPairResponseMapper;

    @Override
    public CompletableFuture<SuccessApiResponse<TokenPairResponse>> login(PasswordAuthorizationRequest request) {
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
}
