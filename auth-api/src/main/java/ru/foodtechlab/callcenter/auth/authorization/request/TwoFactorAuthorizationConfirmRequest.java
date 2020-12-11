package ru.foodtechlab.callcenter.auth.authorization.request;

import com.rcore.domain.auth.authorization.usecases.ConfirmTwoFactorAuthorizationUseCase;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import lombok.Data;

@Data
public class TwoFactorAuthorizationConfirmRequest {
    private String address;
    private ConfirmationCodeEntity.Recipient.SendingType sendingType;
    private String code;

    public ConfirmTwoFactorAuthorizationUseCase.InputValues toInputValues() {
        return ConfirmTwoFactorAuthorizationUseCase.InputValues.of(address, sendingType, code);
    }
}
