package com.gxa.cdut.service;

import com.gxa.cdut.domain.Acount;

public interface IAccountService {

    Acount selectById(int id);

    int insert(Acount acount);

    void updateById(long id,String money);

}
