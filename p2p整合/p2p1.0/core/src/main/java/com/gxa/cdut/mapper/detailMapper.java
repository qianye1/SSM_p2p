package com.gxa.cdut.mapper;

import com.gxa.cdut.domain.Detail;

public interface detailMapper {
    int insert(Detail detail);

    Detail selectById(int id);

    int update(Detail detail);

    Detail selectItems(int id);
}
