package com.gxa.cdut.service;

public interface IVerifyCodeService {

    void sendVerifyCode(String phoneNumber);

    boolean validate(String phoneNumber, String verifyCode);

}
