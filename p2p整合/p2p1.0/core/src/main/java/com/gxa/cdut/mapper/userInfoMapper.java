package com.gxa.cdut.mapper;

import com.gxa.cdut.Query.LoginInfoQueryObject;
import com.gxa.cdut.Query.PageResultSet;
import com.gxa.cdut.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface userInfoMapper {
    int insert(UserInfo userInfo);

    UserInfo selectById(int id);

    UserInfo selectByUsername(String username);

    int selectCountByNameAndType(@Param("username") String username, @Param("usertype") int usertype);

    int selectCount(String username);

    List<UserInfo> selectPage(LoginInfoQueryObject loginInfoQueryObject);

    int selectTotalCount();

}
