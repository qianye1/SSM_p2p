package com.gxa.cdut.mapper;

import com.gxa.cdut.Query.IplogQueryObject;
import com.gxa.cdut.domain.Iplog;

import java.util.List;

public interface iplogMapper {

    int insert(Iplog iplog);

    List<Iplog> selectPage(IplogQueryObject iplogQueryObject);

    int selectTotalCount(IplogQueryObject iplogQueryObject);
}
