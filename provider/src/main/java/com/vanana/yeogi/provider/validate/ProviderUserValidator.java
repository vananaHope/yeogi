package com.vanana.yeogi.provider.validate;

import com.vanana.yeogi.base.validate.ProviderUserValidate;

public class ProviderUserValidator implements ProviderUserValidate {
    @Override
    public boolean isBusinesLsno() {
        return false;
    }

    @Override
    public boolean isPasswordMatching() {
        return false;
    }

    @Override
    public boolean isValidPassword() {
        return false;
    }

    @Override
    public boolean isPhoneNumber() {
        return false;
    }

    @Override
    public boolean isAdult() {
        return false;
    }
}
