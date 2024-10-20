package com.erp.service;

import com.erp.bean.UserInfo;
import com.erp.bean.search.UserInfoSearch;
import com.erp.mapper.UserInfoMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoMapper userInfoMapper;

    private Gson GSON = new Gson();

    public int insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insertUserInfo(userInfo);
    }

    public int updateUserInfoBySid(UserInfo userInfo) {
        return userInfoMapper.updateUserInfoBySid(userInfo);
    }

    public UserInfo checkExistUserInfoToLoginId(UserInfo userInfo) {
        return userInfoMapper.checkExistUserInfoToLoginId(userInfo);
    }

    public List<UserInfo> selectUserInfo(UserInfoSearch search) {
        return userInfoMapper.selectUserInfo(search);

    }

    public int selectUserInfoCount(UserInfoSearch search) {
        return userInfoMapper.selectUserInfoCount(search);
    }

}
