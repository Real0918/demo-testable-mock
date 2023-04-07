package com.example.demo.modular.service.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.common.enums.RemoveEnum;
import com.example.demo.common.enums.StatusEnum;
import com.example.demo.exception.BusinessExceptionEnum;
import com.example.demo.modular.dao.UserMapper;
import com.example.demo.modular.entity.dto.*;
import com.example.demo.modular.entity.po.User;
import com.example.demo.modular.service.IUserService;
import com.example.demo.utils.Asserts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/01/13 11:26:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户列表
     *
     * @param userListDto
     * @return
     */
    @Override
    public Page<User> list(UserListDto userListDto) {

        validate(userListDto);

        Integer pageNumber = userListDto.getPageNumber();
        Integer pageSize = userListDto.getPageSize();
        String search = userListDto.getSearch();

        User userParam = new User();
        userParam.setRemoved(RemoveEnum.ALIVE.getCode());
        EntityWrapper<User> entityWrapper = new EntityWrapper<>(userParam);
        if (StringUtils.isNotBlank(search)) {
            entityWrapper.like("name", search, SqlLike.DEFAULT);
        }

        Page<User> userPage = new Page<>(pageNumber, pageSize);
        List<User> userList = userMapper.selectPage(userPage, entityWrapper);
        userPage.setRecords(userList);

        return userPage;
    }

    /**
     * 获取用户详情
     *
     * @param userDetailDto
     * @return
     */
    @Override
    public User detail(UserDetailDto userDetailDto) {

        String accountId = userDetailDto.getAccountId();

        User userExist = getUserByAccountId(accountId);
        Asserts.notNull(userExist, BusinessExceptionEnum.OBJECT_NOT_EXIST);

        return userExist;
    }

    /**
     * 删除用户
     *
     * @param userDeleteDto
     * @return
     */
    @Override
    public User delete(UserDeleteDto userDeleteDto) {
        String accountId = userDeleteDto.getAccountId();

        User userExist = getUserByAccountId(accountId);
        if (Objects.isNull(userExist)) {
            return null;
        }

        User userUpdate = new User();
        userUpdate.setId(userExist.getId());
        userUpdate.setRemoved(RemoveEnum.DELETE.getCode());

        // 逻辑删除
        userMapper.updateById(userUpdate);

        return userExist;
    }

    /**
     * 修改用户信息
     *
     * @param accountId
     * @param userUpdateDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public User update(String accountId, UserUpdateDto userUpdateDto) {

        User userExist = getUserByAccountId(accountId);
        Asserts.notNull(userExist, BusinessExceptionEnum.OBJECT_NOT_EXIST);

        User userUpdate = new User();
        userUpdate.setId(userExist.getId());
        userUpdate.setUpdateTime(DateUtil.now());

        if (StringUtils.isNotBlank(userUpdateDto.getName())) {
            userUpdate.setName(userUpdateDto.getName());
        }
        if (Objects.nonNull(userUpdateDto.getPermissionStatus())) {
            userUpdate.setPermissionStatus(userUpdateDto.getPermissionStatus());
        }
        if (StringUtils.isNotBlank(userUpdateDto.getDescription())) {
            userUpdate.setDescription(userUpdateDto.getDescription());
        }

        userMapper.updateById(userUpdate);

        return getUserByAccountId(accountId);
    }

    /**
     * 新增用户
     *
     * @param userAddDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User add(UserAddDto userAddDto) {

        User userInsert = new User();
        String accountId = UUID.randomUUID().toString();
        userInsert.setAccountId(accountId);
        userInsert.setPermissionStatus(StatusEnum.ENABLED.getCode());
        userInsert.setRemoved(RemoveEnum.ALIVE.getCode());
        userInsert.setCreateTime(DateUtil.now());
        userInsert.setUpdateTime(userInsert.getCreateTime());

        if (StringUtils.isNotBlank(userAddDto.getName())) {
            userInsert.setName(userAddDto.getName());
        }
        if (StringUtils.isNotBlank(userAddDto.getDescription())) {
            userInsert.setDescription(userAddDto.getDescription());
        }

        userMapper.insert(userInsert);

        return getUserByAccountId(accountId);
    }

    /**
     * 根据accountId查询用户信息
     *
     * @param accountId
     * @return
     */
    private User getUserByAccountId(String accountId) {
        if (StringUtils.isBlank(accountId)) {
            return null;
        }

        User userParam = new User();
        userParam.setAccountId(accountId);
        userParam.setRemoved(RemoveEnum.ALIVE.getCode());
        List<User> userList = userMapper.selectList(new EntityWrapper<>(userParam));
        if (userList.isEmpty()) {
            return null;
        }

        return userList.get(0);
    }

    /**
     * 根据accountId查询用户信息
     *
     * @param name
     * @return
     */
    private List<User> getListByAccountName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }

        List<User> userList = userMapper.selectListByName(name);
        if (userList.isEmpty()) {
            return null;
        }

        return userList;
    }

    /**
     * 参数验证
     */
    private void validate(Object obj) {
        int a = 1;
        int b = 1;
        int sum = a + b;
        System.out.println("validate data");
    }


}
