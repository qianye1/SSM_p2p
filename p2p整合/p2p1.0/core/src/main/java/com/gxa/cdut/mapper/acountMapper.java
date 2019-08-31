package com.gxa.cdut.mapper;

import com.gxa.cdut.domain.Acount;

public interface acountMapper {

    Acount selectById(int id);

    int insert(Acount acount);

    int updateById(Acount account);

}
