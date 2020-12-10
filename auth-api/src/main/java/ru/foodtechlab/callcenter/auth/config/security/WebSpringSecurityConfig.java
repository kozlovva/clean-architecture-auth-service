package ru.foodtechlab.callcenter.auth.config.security;

import com.rcore.rest.api.commons.routes.BaseRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
@Configuration
public class WebSpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(BaseRoutes.NOT_SECURE + "/**").permitAll()
                .and()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                .and()
                    .cors()
                .and()
                    .csrf().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .logout().disable()
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling()
                    .authenticationEntryPoint(tokenAuthenticationEntryPoint);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/api-docs")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui/index.html")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/configuration/**")
                .antMatchers("/webjars/**")
                .antMatchers("/public");
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }

}
