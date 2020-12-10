package ru.foodtechlab.callcenter.auth.roles.request;

import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.usecases.CreateRoleUseCase;
import lombok.Data;

import java.util.List;

@Data
public class CreateRoleRequest {

    private String name;
    private List<String> accesses;
    private Boolean hasBoundlessAccess;
    private List<RoleEntity.AuthType> availableAuthTypes;

    public CreateRoleUseCase.InputValues toInputValues() {
        return CreateRoleUseCase.InputValues.of(name, accesses, hasBoundlessAccess, availableAuthTypes);
    }

}
