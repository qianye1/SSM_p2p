package com.gxa.cdut.service.Impl;

import com.gxa.cdut.Query.BidRequestQueryObject;
import com.gxa.cdut.Query.PageResultSet;
import com.gxa.cdut.domain.BidRequest;
import com.gxa.cdut.mapper.bidRequestMapper;
import com.gxa.cdut.mapper.detailMapper;
import com.gxa.cdut.mapper.userInfoMapper;
import com.gxa.cdut.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class BidRequestServiceImpl implements IBidRequestService {

    @Autowired
    com.gxa.cdut.mapper.bidRequestMapper bidRequestMapper;

    @Autowired
    com.gxa.cdut.mapper.userInfoMapper userInfoMapper;

    @Override
    public BidRequest selectById(int id) {
        BidRequest one = bidRequestMapper.selectById(id);
        one.setCreateUser(userInfoMapper.selectById(one.getCreateuser_id()));
        return one;
    }

    @Override
    public int insert(BidRequest bidRequest) {
        bidRequestMapper.insert(bidRequest);
        return 0;
    }



    @Override
    public PageResultSet selectPage(BidRequestQueryObject bidRequestQueryObject) {

        PageResultSet pageResultSet;
        int count = bidRequestMapper.selectCount(bidRequestQueryObject);

        if(count>0){
            List<BidRequest> list = bidRequestMapper.selectPage(bidRequestQueryObject);
            for(BidRequest one:list){
                one.setCreateUser(userInfoMapper.selectById(one.getCreateuser_id()));
            }
            pageResultSet = new PageResultSet(list,count,bidRequestQueryObject.getCurrentPage(),bidRequestQueryObject.getPageSize());
            return pageResultSet;
        }else{
            return PageResultSet.empty();
        }


    }

    @Override
    public int updateConfirm(BidRequest bidRequest) {

        bidRequestMapper.updateConfirm(bidRequest);

        return 0;
    }
}
