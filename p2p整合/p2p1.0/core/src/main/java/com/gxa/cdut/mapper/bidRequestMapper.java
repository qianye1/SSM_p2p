package com.gxa.cdut.mapper;

import com.gxa.cdut.Query.BidRequestQueryObject;
import com.gxa.cdut.domain.BidRequest;

import java.util.List;

public interface bidRequestMapper {

    public BidRequest selectById(int id);

    public int insert(BidRequest bidRequest);

    public int selectCount(BidRequestQueryObject bidRequestQueryObject);

    public List<BidRequest> selectPage(BidRequestQueryObject bidRequestQueryObject);

    public int updateConfirm(BidRequest bidRequest);

}
