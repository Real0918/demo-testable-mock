package com.example.demo.modular.entity.vo;


import com.example.demo.modular.entity.po.User;

/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/1/13 16:40:05
 */
public class UserVo extends User {

    private String accountName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
