package com.gxa.cdut.service;

import com.gxa.cdut.Query.IplogQueryObject;
import com.gxa.cdut.Query.PageResultSet;
import com.gxa.cdut.domain.Iplog;

public interface IIplogService {

    int insert(Iplog iplog);

    PageResultSet getPage(IplogQueryObject iplogQueryObject);

}
