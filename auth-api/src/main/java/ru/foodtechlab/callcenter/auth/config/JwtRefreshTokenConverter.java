package ru.foodtechlab.callcenter.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.rcore.domain.security.exceptions.ConvertingTokenException;
import com.rcore.domain.security.exceptions.InvalidTokenException;
import com.rcore.domain.security.exceptions.ParsingTokenException;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtRefreshTokenConverter implements TokenConverter<RefreshTokenData> {

    @Value("${ftl.security.jwt.secret}")
    private String secret;

    private final ObjectMapper objectMapper;

    @Override
    public String convert(RefreshTokenData token) throws ConvertingTokenException {
        try {
            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(objectMapper.writeValueAsString(token)));
            jwsObject.sign(new MACSigner(secret));
            return jwsObject.serialize();
        } catch (Exception e) {
            log.error("Refresh token data converting error", e);
            throw new ConvertingTokenException();
        }
    }

    @Override
    public RefreshTokenData parse(String token) throws ParsingTokenException {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            return objectMapper.readValue(jwsObject.getPayload().toString(), RefreshTokenData.class);
        } catch (Exception e) {
            log.error("Refresh token data parsing error", e);
            throw new ParsingTokenException();
        }
    }

    @Override
    public void validateStringToken(String token) throws InvalidTokenException {

    }
}
