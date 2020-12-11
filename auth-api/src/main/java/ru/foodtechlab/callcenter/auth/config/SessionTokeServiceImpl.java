package ru.foodtechlab.callcenter.auth.config;

import com.rcore.domain.auth.token.port.SessionTokenService;
import com.rcore.rest.api.commons.header.WebHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class SessionTokeServiceImpl implements SessionTokenService {

    @Override
    public String getSessionAccessToken() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest()
                .getHeader(WebHeaders.X_AUTH_TOKEN);
    }
}
