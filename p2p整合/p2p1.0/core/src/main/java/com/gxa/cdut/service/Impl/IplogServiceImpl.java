package com.gxa.cdut.service.Impl;

import com.gxa.cdut.Query.IplogQueryObject;
import com.gxa.cdut.Query.PageResultSet;
import com.gxa.cdut.domain.Iplog;
import com.gxa.cdut.mapper.iplogMapper;
import com.gxa.cdut.service.IIplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IplogServiceImpl implements IIplogService {

    @Autowired
    private com.gxa.cdut.mapper.iplogMapper iplogMapper;

    @Override
    public int insert(Iplog iplog) {
        iplogMapper.insert(iplog);
        return 0;
    }

    @Override
    public PageResultSet getPage(IplogQueryObject iplogQueryObject) {
        PageResultSet pageResultSet;

        int count = iplogMapper.selectTotalCount(iplogQueryObject);

        if(count>0){

            List<Iplog> list = iplogMapper.selectPage(iplogQueryObject);

            pageResultSet = new PageResultSet(list,count,iplogQueryObject.getCurrentPage(),iplogQueryObject.getPageSize());

        }else {
            return PageResultSet.empty();
        }

        return pageResultSet;
    }


}
