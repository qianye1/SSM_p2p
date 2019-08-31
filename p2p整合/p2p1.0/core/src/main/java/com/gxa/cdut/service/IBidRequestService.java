package com.gxa.cdut.service;

import com.gxa.cdut.Query.BidRequestQueryObject;
import com.gxa.cdut.Query.PageResultSet;
import com.gxa.cdut.domain.BidRequest;


public interface IBidRequestService {

    BidRequest selectById(int id);

    int insert(BidRequest bidRequest);

    PageResultSet selectPage(BidRequestQueryObject bidRequestQueryObject);

    int updateConfirm(BidRequest bidRequest);

}
