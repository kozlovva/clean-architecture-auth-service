package ru.foodtechlab.callcenter.auth.roles;

import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.commons.routes.BaseRoutes;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.foodtechlab.callcenter.auth.config.security.CurrentUser;
import ru.foodtechlab.callcenter.auth.config.security.UserPrincipal;
import ru.foodtechlab.callcenter.auth.roles.request.CreateRoleRequest;
import ru.foodtechlab.callcenter.auth.roles.response.RoleResponse;

import java.util.concurrent.CompletableFuture;

@Secured("SUPER_USER")
@RestController
public interface RoleResource {

    @GetMapping(value = Routes.BY_ID)
    CompletableFuture<SuccessApiResponse<RoleResponse>> findById(@CurrentUser UserPrincipal userPrincipal, @PathVariable String id);

    @PostMapping(value = Routes.ROOT)
    CompletableFuture<SuccessApiResponse<RoleResponse>> create(@RequestBody CreateRoleRequest createRoleRequest);

}
