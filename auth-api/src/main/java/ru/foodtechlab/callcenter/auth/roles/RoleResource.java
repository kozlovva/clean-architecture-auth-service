package ru.foodtechlab.callcenter.auth.roles;

import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.commons.routes.BaseRoutes;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.foodtechlab.callcenter.auth.roles.request.CreateRoleRequest;
import ru.foodtechlab.callcenter.auth.roles.response.RoleResponse;

import java.util.concurrent.CompletableFuture;

@Secured("SUPER_USER")
@RestController
public interface RoleResource {

    public final String RESOURCE = "/roles";

    @GetMapping(value = BaseRoutes.API + BaseRoutes.V1 + RESOURCE + "/{id}")
    CompletableFuture<SuccessApiResponse<RoleResponse>> findById(@PathVariable String id);

    @PostMapping(value = BaseRoutes.API + BaseRoutes.V1 + RESOURCE)
    CompletableFuture<SuccessApiResponse<RoleResponse>> create(@RequestBody CreateRoleRequest createRoleRequest);

}
