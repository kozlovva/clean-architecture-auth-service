package ru.foodtechlab.callcenter.auth.roles;

import com.rcore.domain.auth.role.usecases.CreateRoleUseCase;
import com.rcore.domain.auth.role.usecases.FindRoleByIdUseCase;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import ru.foodtechlab.callcenter.auth.config.security.UserPrincipal;
import ru.foodtechlab.callcenter.auth.roles.request.CreateRoleRequest;
import ru.foodtechlab.callcenter.auth.roles.response.RoleResponse;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
public class RoleController implements RoleResource {

    private final UseCaseExecutor useCaseExecutor;
    private final FindRoleByIdUseCase findRoleByIdUseCase;
    private final CreateRoleUseCase createRoleUseCase;

    @Override
    public CompletableFuture<SuccessApiResponse<RoleResponse>> findById(UserPrincipal userPrincipal, String id) {
        return useCaseExecutor.execute(
                findRoleByIdUseCase,
                AbstractFindByIdUseCase.InputValues.of(id),
                output -> output.getResult()
                        .map(role -> SuccessApiResponse.of(RoleResponse.builder()
                                .id(role.getId())
                                .build()))
                        .orElse(SuccessApiResponse.of())
        );
    }

    @Override
    public CompletableFuture<SuccessApiResponse<RoleResponse>> create(CreateRoleRequest createRoleRequest) {
        return useCaseExecutor.execute(
                createRoleUseCase,
                createRoleRequest.toInputValues(),
                outputValues -> SuccessApiResponse.of(RoleResponse.from(outputValues.getEntity()))
        );
    }
}
