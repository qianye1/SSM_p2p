package com.gxa.cdut.service;

import com.gxa.cdut.domain.BidRequestAuditHistory;

import java.util.List;

public interface IBidRequestAuditHistoryService {

    int insert(BidRequestAuditHistory bidRequestAuditHistory);

    BidRequestAuditHistory selectById(int id);

    List<BidRequestAuditHistory> selectByBidId(int id);

}
