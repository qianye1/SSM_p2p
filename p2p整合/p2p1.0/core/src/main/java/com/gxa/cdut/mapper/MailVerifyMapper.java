package com.gxa.cdut.mapper;

import com.gxa.cdut.domain.MailVerify;

public interface MailVerifyMapper {
    int insert(MailVerify mailVerify);
    MailVerify selectByUUID(String uuid);
}
