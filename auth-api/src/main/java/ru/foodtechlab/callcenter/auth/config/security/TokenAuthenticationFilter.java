package ru.foodtechlab.callcenter.auth.config.security;

import com.rcore.rest.api.commons.header.WebHeaders;
import com.rcore.rest.api.commons.routes.BaseRoutes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import ru.foodtechlab.callcenter.auth.config.security.exceptions.AuthenticationApiException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationFailureHandler authenticationFailureHandler) {
        super(BaseRoutes.API + "/**");
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        try {
            String token = httpServletRequest.getHeader(WebHeaders.X_AUTH_TOKEN);

            if (token == null) {
                TokenAuthentication authentication = new TokenAuthentication();
                authentication.setAuthenticated(false);
                return authentication;
            }

            TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
            Authentication authentication = getAuthenticationManager().authenticate(tokenAuthentication);
            return authentication;
        } catch (Exception e) {
            log.error("Attempt authentication error", e);
            throw new AuthenticationApiException("Attempt authentication error", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        request.getRequestDispatcher(request.getServletPath() + request.getPathInfo())
                .forward(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
