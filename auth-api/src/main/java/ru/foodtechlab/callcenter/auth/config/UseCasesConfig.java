package ru.foodtechlab.callcenter.auth.config;

import com.rcore.domain.auth.authorization.port.AuthorizationIdGenerator;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.auth.authorization.usecases.*;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeGenerator;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeIdGenerator;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.auth.confirmationCode.port.impl.ConfirmationCodeGeneratorImpl;
import com.rcore.domain.auth.confirmationCode.usecases.CreateConfirmationCodeUseCase;
import com.rcore.domain.auth.credential.port.CredentialIdGenerator;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.credential.port.impl.CredentialServiceImpl;
import com.rcore.domain.auth.credential.port.impl.PasswordCryptographerImpl;
import com.rcore.domain.auth.credential.usecases.*;
import com.rcore.domain.auth.role.port.RoleIdGenerator;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.auth.role.usecases.CreateRoleUseCase;
import com.rcore.domain.auth.role.usecases.FindRoleByIdUseCase;
import com.rcore.domain.auth.role.usecases.FindRoleByNameUseCase;
import com.rcore.domain.auth.token.port.*;
import com.rcore.domain.auth.token.port.impl.TokenSaltGeneratorImpl;
import com.rcore.domain.auth.token.usecases.*;
import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.domain.commons.usecase.impl.UseCaseExecutorImpl;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.CredentialService;
import com.rcore.domain.security.port.TokenConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурируем usecases
 */
@Configuration
public class UseCasesConfig {

    @Bean
    public TokenSaltGenerator tokenSaltGenerator() {
        return new TokenSaltGeneratorImpl();
    }

    @Bean
    public PasswordCryptographer passwordCryptographer() {
        return new PasswordCryptographerImpl();
    }

    @Bean
    public ConfirmationCodeGenerator confirmationCodeGenerator() {
        return new ConfirmationCodeGeneratorImpl(4);
    }

    @Bean
    public CredentialService credentialService(CredentialRepository credentialRepository, TokenConverter<AccessTokenData> tokenConverter, RoleRepository roleRepository, AccessTokenRepository accessTokenRepository) {
        return new CredentialServiceImpl(credentialRepository, tokenConverter, roleRepository, accessTokenRepository);
    }

    @Bean
    public UseCaseExecutor useCaseExecutor() {
        return new UseCaseExecutorImpl();
    }

    @Bean
    public FindRoleByIdUseCase findRoleByIdUseCase(RoleRepository roleRepository) {
        return new FindRoleByIdUseCase(roleRepository);
    }

    @Bean
    public FindRoleByNameUseCase findRoleByNameUseCase(RoleRepository roleRepository) {
        return new FindRoleByNameUseCase(roleRepository);
    }

    @Bean
    public CreateRoleUseCase createRoleUseCase(RoleRepository roleRepository, RoleIdGenerator roleIdGenerator) {
        return new CreateRoleUseCase(roleRepository, roleIdGenerator);
    }

    @Bean
    public FindCredentialByUsernameUseCase findCredentialByUsernameUseCase(CredentialRepository credentialRepository) {
        return new FindCredentialByUsernameUseCase(credentialRepository);
    }

    @Bean
    public FindCredentialByPhoneUseCase findCredentialByPhoneUseCase(CredentialRepository credentialRepository) {
        return new FindCredentialByPhoneUseCase(credentialRepository);
    }

    @Bean
    public FindCredentialByEmailUseCase findCredentialByEmailUseCase(CredentialRepository credentialRepository) {
        return new FindCredentialByEmailUseCase(credentialRepository);
    }

    @Bean
    public CreateCredentialUseCase createCredentialUseCase(
            CredentialRepository credentialRepository,
            CredentialIdGenerator credentialIdGenerator,
            FindRoleByIdUseCase findRoleByIdUseCase,
            FindRoleByNameUseCase findRoleByNameUseCase,
            FindCredentialByPhoneUseCase findCredentialByPhoneUseCase,
            FindCredentialByEmailUseCase findCredentialByEmailUseCase,
            FindCredentialByUsernameUseCase findCredentialByUsernameUseCase,
            PasswordCryptographer passwordCryptographer
    ) {
        return new CreateCredentialUseCase(
                credentialRepository,
                credentialIdGenerator,
                findRoleByIdUseCase,
                findRoleByNameUseCase,
                findCredentialByPhoneUseCase,
                findCredentialByEmailUseCase,
                findCredentialByUsernameUseCase,
                passwordCryptographer
        );
    }

    @Bean
    public CreateAuthorizationUseCase createAuthorizationUseCase(AuthorizationRepository authorizationRepository, AuthorizationIdGenerator authorizationIdGenerator) {
        return new CreateAuthorizationUseCase(authorizationRepository, authorizationIdGenerator);
    }

    @Bean
    public CreateRefreshTokenUseCase createRefreshTokenUseCase(RefreshTokenRepository refreshTokenRepository, RefreshTokenIdGenerator refreshTokenIdGenerator, TokenSaltGenerator tokenSaltGenerator) {
        return new CreateRefreshTokenUseCase(refreshTokenRepository, refreshTokenIdGenerator, tokenSaltGenerator);
    }

    @Bean
    public CreateAccessTokenUseCase createAccessTokenUseCase(AccessTokenRepository accessTokenRepository, AccessTokenIdGenerator accessTokenIdGenerator) {
        return new CreateAccessTokenUseCase(accessTokenRepository, accessTokenIdGenerator);
    }

    @Bean
    public PasswordAuthorizationUseCase passwordAuthorizationUseCase(
            CreateAuthorizationUseCase createAuthorizationUseCase,
            CredentialRepository credentialRepository,
            PasswordCryptographer passwordCryptographer,
            CreateRefreshTokenUseCase createRefreshTokenUseCase,
            CreateAccessTokenUseCase createAccessTokenUseCase
    ) {
        return new PasswordAuthorizationUseCase(createAuthorizationUseCase, credentialRepository, passwordCryptographer, createRefreshTokenUseCase, createAccessTokenUseCase);
    }

    @Bean
    public DeactivateAccessTokensByRefreshToken deactivateAccessTokensByRefreshToken(AccessTokenRepository accessTokenRepository) {
        return new DeactivateAccessTokensByRefreshToken(accessTokenRepository);
    }

    @Bean
    public ExpireRefreshTokenUseCase expireRefreshTokenUseCase(RefreshTokenRepository refreshTokenRepository, DeactivateAccessTokensByRefreshToken deactivateAccessTokensByRefreshToken) {
        return new ExpireRefreshTokenUseCase(refreshTokenRepository, deactivateAccessTokensByRefreshToken);
    }

    @Bean
    public RefreshAccessTokenUseCase refreshAccessTokenUseCase(
            TokenConverter<RefreshTokenData> tokenConverter,
            CredentialRepository credentialRepository,
            RefreshTokenRepository refreshTokenRepository,
            ExpireRefreshTokenUseCase expireRefreshTokenUseCase,
            CreateAccessTokenUseCase createAccessTokenUseCase
    ) {
        return new RefreshAccessTokenUseCase(tokenConverter, credentialRepository, refreshTokenRepository, expireRefreshTokenUseCase, createAccessTokenUseCase);
    }

    @Bean
    public LogoutUseCase logoutUseCase(TokenConverter<AccessTokenData> accessTokenDataTokenConverter, RefreshTokenRepository refreshTokenRepository, AccessTokenRepository accessTokenRepository) {
        return new LogoutUseCase(accessTokenDataTokenConverter, refreshTokenRepository, accessTokenRepository);
    }

    @Bean
    public CreateConfirmationCodeUseCase createConfirmationCodeUseCase(ConfirmationCodeRepository confirmationCodeRepository, ConfirmationCodeIdGenerator confirmationCodeIdGenerator, ConfirmationCodeGenerator confirmationCodeGenerator) {
        return new CreateConfirmationCodeUseCase(confirmationCodeRepository, confirmationCodeIdGenerator, confirmationCodeGenerator);
    }

    @Bean
    public InitTwoFactorAuthorizationUseCase initTwoFactorAuthorizationUseCase(CreateAuthorizationUseCase createAuthorizationUseCase, CreateConfirmationCodeUseCase createConfirmationCodeUseCase, FindCredentialByEmailUseCase findCredentialByEmailUseCase, FindCredentialByPhoneUseCase findCredentialByPhoneUseCase) {
        return new InitTwoFactorAuthorizationUseCase(createAuthorizationUseCase, createConfirmationCodeUseCase, findCredentialByEmailUseCase, findCredentialByPhoneUseCase);
    }

    @Bean
    public FindAuthorizationByIdUseCase findAuthorizationByIdUseCase(AuthorizationRepository authorizationRepository) {
        return new FindAuthorizationByIdUseCase(authorizationRepository);
    }

    @Bean
    public FindCredentialByIdUseCase findCredentialByIdUseCase(CredentialRepository credentialRepository) {
        return new FindCredentialByIdUseCase(credentialRepository);
    }

    @Bean
    public TransferAuthorizationToSuccessStatusUseCase transferAuthorizationToSuccessStatusUseCase(AuthorizationRepository authorizationRepository) {
        return new TransferAuthorizationToSuccessStatusUseCase(authorizationRepository);
    }

    @Bean
    public ConfirmTwoFactorAuthorizationUseCase confirmTwoFactorAuthorizationUseCase(
            ConfirmationCodeRepository confirmationCodeRepository,
            FindAuthorizationByIdUseCase findAuthorizationByIdUseCase,
            FindCredentialByIdUseCase findCredentialByIdUseCase,
            CreateAccessTokenUseCase createAccessTokenUseCase,
            CreateRefreshTokenUseCase createRefreshTokenUseCase,
            TransferAuthorizationToSuccessStatusUseCase transferAuthorizationToSuccessStatusUseCase
    ) {
        return new ConfirmTwoFactorAuthorizationUseCase(confirmationCodeRepository, findAuthorizationByIdUseCase, findCredentialByIdUseCase, createAccessTokenUseCase, createRefreshTokenUseCase, transferAuthorizationToSuccessStatusUseCase);
    }

}
