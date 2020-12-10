package ru.foodtechlab.callcenter.auth.roles.response;

import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RoleResponse {
    private String id;
    private String name;
    private List<String> accesses;
    private Boolean hasBoundlessAccess;
    private List<RoleEntity.AuthType> availableAuthTypes;

    public static RoleResponse from(RoleEntity roleEntity) {
        return RoleResponse.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .accesses(roleEntity.getAccesses())
                .availableAuthTypes(roleEntity.getAvailableAuthTypes())
                .hasBoundlessAccess(roleEntity.getHasBoundlessAccess())
                .build();
    }

}
