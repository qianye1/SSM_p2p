package com.gxa.cdut.mapper;

import com.gxa.cdut.domain.BidRequestAuditHistory;

import java.util.List;

public interface bidRequestAuditHistoryMapper {

    int insert(BidRequestAuditHistory bidRequestAuditHistory);

    BidRequestAuditHistory selectById(int id);

    List<BidRequestAuditHistory> selectByBidId(int id);

}
