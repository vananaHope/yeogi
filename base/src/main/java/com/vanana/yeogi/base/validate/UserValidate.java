package com.vanana.yeogi.base.validate;

public interface UserValidate{
    boolean isPasswordMatching();
    boolean isValidPassword();
    boolean isPhoneNumber();
    boolean isAdult();
}
