package ru.foodtechlab.callcenter.auth.authorization.request;

import com.rcore.domain.auth.authorization.usecases.InitTwoFactorAuthorizationUseCase;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import lombok.Data;

@Data
public class InitTwoFactorAuthorizationRequest {
    private String address;
    private ConfirmationCodeEntity.Recipient.SendingType sendingType;

    public InitTwoFactorAuthorizationUseCase.InputValues toInputValues(Long ttl) {
        return InitTwoFactorAuthorizationUseCase.InputValues.of(address, sendingType, ttl);
    }
}
