package ru.foodtechlab.callcenter.auth.credential.response;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CredentialResponse {
    private String id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private List<Role> roles;
    private CredentialEntity.Status status;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class Role {
        private String roleId;
        private boolean isBlocked;

        public static Role from(CredentialEntity.Role role) {
            return Role.builder()
                    .roleId(role.getRoleId())
                    .isBlocked(role.isBlocked())
                    .build();
        }
    }

    public static CredentialResponse from(CredentialEntity credentialEntity) {
        return CredentialResponse.builder()
                .id(credentialEntity.getId())
                .email(credentialEntity.getEmail())
                .phone(credentialEntity.getPhone())
                .roles(credentialEntity.getRoles()
                        .stream()
                        .map(role -> CredentialResponse.Role.from(role))
                        .collect(Collectors.toList()))
                .status(credentialEntity.getStatus())
                .username(credentialEntity.getUsername())
                .build();
    }

}
