package ru.foodtechlab.callcenter.auth.credential;

import com.rcore.domain.auth.credential.usecases.CreateCredentialUseCase;
import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.foodtechlab.callcenter.auth.credential.request.CreateCredentialRequest;
import ru.foodtechlab.callcenter.auth.credential.response.CredentialResponse;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
public class CredentialController implements CredentialResource {

    private final UseCaseExecutor useCaseExecutor;
    private final CreateCredentialUseCase createCredentialUseCase;

    @Override
    public CompletableFuture<SuccessApiResponse<CredentialResponse>> create(CreateCredentialRequest createCredentialRequest) {
        return useCaseExecutor.execute(
                createCredentialUseCase,
                createCredentialRequest.toInputValues(),
                o -> SuccessApiResponse.of(CredentialResponse.from(o.getEntity()))
        );
    }
}
