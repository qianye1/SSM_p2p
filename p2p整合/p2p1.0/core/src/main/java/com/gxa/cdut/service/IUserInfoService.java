package com.gxa.cdut.service;

import com.gxa.cdut.Query.LoginInfoQueryObject;
import com.gxa.cdut.Query.PageResultSet;
import com.gxa.cdut.domain.UserInfo;

import javax.servlet.http.HttpServletRequest;

public interface IUserInfoService {
    int insert(String username, String password);

    UserInfo selctByUsername(String username);

    UserInfo selectByUsernameAndType(String username,int type);

    UserInfo selectById(int id);

    int selectCount(String username);

    PageResultSet loginInfoTable(LoginInfoQueryObject loginInfoQueryObject);

}
