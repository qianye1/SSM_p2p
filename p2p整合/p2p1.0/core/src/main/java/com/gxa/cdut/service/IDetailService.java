package com.gxa.cdut.service;

import com.gxa.cdut.domain.Acount;
import com.gxa.cdut.domain.Detail;

public interface IDetailService {
    Detail selectById(int id);

    Detail selectByName(String name);

    int insert(Detail detail);

    int update(Detail detail);

    Detail selectItems(int id);

    /**
     *
     * 用户绑定手机
     *
     * @param phoneNumber
     * @param verifyCode
     */
    void bindPhone(String phoneNumber, String verifyCode);

    /**
     * 绑定邮箱
     *
     * @param uuid
     */
    void bindEmail(String uuid);


}
