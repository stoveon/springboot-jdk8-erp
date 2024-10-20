package com.erp.mapper;

import com.erp.bean.UserInfo;
import com.erp.bean.search.UserInfoSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    int insertUserInfo(UserInfo userInfo);

    int updateUserInfoBySid(UserInfo userInfo);

    UserInfo checkExistUserInfoToLoginId(UserInfo userInfo);

    List<UserInfo> selectUserInfo(UserInfoSearch search);

    int selectUserInfoCount(UserInfoSearch search);
}
