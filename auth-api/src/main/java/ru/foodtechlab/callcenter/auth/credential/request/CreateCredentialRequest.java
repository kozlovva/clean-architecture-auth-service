package ru.foodtechlab.callcenter.auth.credential.request;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.usecases.CreateCredentialUseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class CreateCredentialRequest {
    private String username;
    private String password;
    private String phone;
    private String email;
    private List<Role> roles;
    private CredentialEntity.Status status;

    public CreateCredentialUseCase.InputValues toInputValues() {
        return CreateCredentialUseCase.InputValues.builder()
                .username(username)
                .email(email)
                .phone(phone)
                .password(password)
                .roles(Optional.ofNullable(roles)
                        .map(roles -> roles.stream()
                                .map(role -> new CreateCredentialUseCase.InputValues.Role(role.getRoleId(), role.getName(), role.isBlocked()))
                                .collect(Collectors.toList()))
                        .orElse(null))
                .status(status)
                .build();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Role {
        private String roleId;
        private String name;
        private boolean isBlocked;
    }
}
