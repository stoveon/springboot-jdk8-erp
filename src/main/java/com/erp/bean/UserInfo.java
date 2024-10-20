package com.erp.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class UserInfo {

    private int sid;

    private String loginId;

    private String password;

    private String name;

    private String address;

    private String phoneNumber;

    private Date createdAt;

    private Date updatedAt;
}
