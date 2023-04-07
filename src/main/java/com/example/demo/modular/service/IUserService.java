package com.example.demo.modular.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.demo.modular.entity.dto.*;
import com.example.demo.modular.entity.po.User;

/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/01/13 11:26:32
 */
public interface IUserService extends IService<User> {

    /**
     * 获取用户列表
     *
     * @param userListDto
     * @return
     */
    Page<User> list(UserListDto userListDto);

    /**
     * 获取用户详情
     *
     * @param userDetailDto
     * @return
     */
    User detail(UserDetailDto userDetailDto);

    /**
     * 删除用户
     *
     * @param userDeleteDto
     * @return
     */
    User delete(UserDeleteDto userDeleteDto);

    /**
     * 修改用户信息
     *
     * @param accountId
     * @param userUpdateDto
     * @return
     */
    User update(String accountId, UserUpdateDto userUpdateDto);

    /**
     * 新增用户
     *
     * @param userAddDto
     * @return
     */
    User add(UserAddDto userAddDto);
}
