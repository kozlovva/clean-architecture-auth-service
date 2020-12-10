package ru.foodtechlab.callcenter.auth.authorization.mappers;

import com.rcore.domain.auth.token.entity.TokenPair;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.foodtechlab.callcenter.auth.authorization.response.TokenPairResponse;

@RequiredArgsConstructor
@Component
public class TokenPairResponseMapper {

    private final TokenConverter<AccessTokenData> accessTokenDataTokenConverter;
    private final TokenConverter<RefreshTokenData> refreshTokenDataTokenConverter;

    public TokenPairResponse map(TokenPair tokenPair) {
        return new TokenPairResponse(
                accessTokenDataTokenConverter.convert(tokenPair.getAccessToken().toAccessTokenData()),
                refreshTokenDataTokenConverter.convert(tokenPair.getRefreshToken().toRefreshTokenData())
        );
    }

}
