package com.gxa.cdut.service.Impl;

import com.gxa.cdut.domain.BidRequestAuditHistory;
import com.gxa.cdut.mapper.bidRequestAuditHistoryMapper;
import com.gxa.cdut.service.IBidRequestAuditHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidRequestAuditHistoryServiceImpl implements IBidRequestAuditHistoryService {

    @Autowired
    private com.gxa.cdut.mapper.bidRequestAuditHistoryMapper bidRequestAuditHistoryMapper;

    @Override
    public int insert(BidRequestAuditHistory bidRequestAuditHistory) {
        System.out.println(bidRequestAuditHistory.getBidRequestId());
        System.out.println(bidRequestAuditHistory.getAuditType());
        System.out.println(bidRequestAuditHistory.getAuditTime());
        System.out.println(bidRequestAuditHistory.getApplyTime());
        bidRequestAuditHistoryMapper.insert(bidRequestAuditHistory);
        return 1;
    }

    @Override
    public BidRequestAuditHistory selectById(int id) {
        return bidRequestAuditHistoryMapper.selectById(id);
    }

    @Override
    public List<BidRequestAuditHistory> selectByBidId(int id) {

        return bidRequestAuditHistoryMapper.selectByBidId(id);

    }
}
