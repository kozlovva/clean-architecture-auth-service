package ru.foodtechlab.callcenter.auth.credential;

import com.rcore.rest.api.commons.response.SuccessApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.foodtechlab.callcenter.auth.credential.request.CreateCredentialRequest;
import ru.foodtechlab.callcenter.auth.credential.response.CredentialResponse;

import java.util.concurrent.CompletableFuture;

@RestController
public interface CredentialResource {

    @PostMapping(value = Routes.ROOT)
    CompletableFuture<SuccessApiResponse<CredentialResponse>> create(@RequestBody CreateCredentialRequest createCredentialRequest);

}
