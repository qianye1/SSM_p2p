package com.gxa.cdut.service.Impl;

import com.gxa.cdut.Query.LoginInfoQueryObject;
import com.gxa.cdut.Query.PageResultSet;
import com.gxa.cdut.domain.Acount;
import com.gxa.cdut.domain.Detail;
import com.gxa.cdut.domain.UserInfo;
import com.gxa.cdut.mapper.acountMapper;
import com.gxa.cdut.mapper.detailMapper;
import com.gxa.cdut.mapper.userInfoMapper;
import com.gxa.cdut.service.IAccountService;
import com.gxa.cdut.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private com.gxa.cdut.mapper.userInfoMapper userInfoMapper;

    @Autowired
    private com.gxa.cdut.mapper.acountMapper acountMapper;

    @Autowired
    private com.gxa.cdut.mapper.detailMapper detailMapper;

    @Override
    public int insert(String username, String password) {

        int count = userInfoMapper.selectCountByNameAndType(username,UserInfo.STATE_ACTIVE);
        if(count<=0){
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.setPassword(password);
            userInfo.setState(UserInfo.STATE_ACTIVE);
            userInfo.setAdmin(0);
            userInfo.setUsertype(UserInfo.TYPE_USER);


            userInfoMapper.insert(userInfo);

            Acount acount = new Acount();
            acount.setId(userInfo.getId());
            acountMapper.insert(acount);

            Detail detail = new Detail();
            detail.setId(userInfo.getId());
            detailMapper.insert(detail);


        }else{
            throw new RuntimeException("用户名已存在！");
        }

        return 0;

    }

    @Override
    public UserInfo selctByUsername(String username) {

        int count = userInfoMapper.selectCount(username);
        if(count<=0){
            throw new RuntimeException("用户名不存在！");
        }

        UserInfo userInfo = userInfoMapper.selectByUsername(username);

        return userInfo;

    }

    public UserInfo selectByUsernameAndType(String username,int type){

        int count = userInfoMapper.selectCountByNameAndType(username,type);

        if(count<=0){
            throw new RuntimeException("用户名或密码错误！");
        }

        UserInfo userInfo = userInfoMapper.selectByUsername(username);

        return userInfo;

    }

    @Override
    public UserInfo selectById(int id) {

        return userInfoMapper.selectById(id);
    }

    @Override
    public int selectCount(String name) {
        int count = userInfoMapper.selectCount(name);
        return count;
    }

    @Override
    public PageResultSet loginInfoTable(LoginInfoQueryObject loginInfoQueryObject) {

        int count  = userInfoMapper.selectTotalCount();
        PageResultSet pageResultSet;

        if(count>0) {

            List<UserInfo> list = userInfoMapper.selectPage(loginInfoQueryObject);

            pageResultSet = new PageResultSet(list,count,loginInfoQueryObject.getCurrentPage(),loginInfoQueryObject.getPageSize());

        }else{
            return PageResultSet.empty();
        }
        return pageResultSet;
    }
}
