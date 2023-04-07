package com.example.demo.modular.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.base.ResponseResult;
import com.example.demo.common.constant.Constant;
import com.example.demo.exception.BusinessExceptionEnum;
import com.example.demo.modular.entity.dto.*;
import com.example.demo.modular.entity.po.User;
import com.example.demo.modular.service.IUserService;
import com.example.demo.utils.LogUtil;
import com.example.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/01/13 11:26:32
 */
@Api(value = "user", description = "用户信息")
@RestController
@RequestMapping(Constant.API_PREFIX + "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;


    @ApiOperation(value = "获取用户列表", notes = "获取用户列表", response = HttpMessage.class)
    @GetMapping("/list")
    public ResponseResult list(@ApiParam(value = "页码，默认为1", defaultValue = "1") @RequestParam(name = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                               @ApiParam(value = "分页大小，默认为10", defaultValue = "10") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               @ApiParam(value = "模糊查询参数") @RequestParam(name = "search", required = false) String search
    ) {

        UserListDto userListDto = new UserListDto.Builder(pageNumber, pageSize)
                .setSearch(search)
                .build();
        LogUtil.printLog(logger, "获取用户列表 接口参数：{}", JSONObject.toJSONString(userListDto));

        Page<User> userPage = userService.list(userListDto);
        return ResultUtil.result(BusinessExceptionEnum.SUCCESS, userPage);
    }

    @ApiOperation(value = "获取用户详情", notes = "获取用户详情", response = HttpMessage.class)
    @GetMapping("/detail/{accountId}")
    public ResponseResult detail(@ApiParam(value = "用户accountId", required = true) @PathVariable("accountId") String accountId
    ) {

        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setAccountId(accountId);
        LogUtil.printLog(logger, "获取用户详情 接口参数：{}", JSONObject.toJSONString(userDetailDto));

        User user = userService.detail(userDetailDto);
        return ResultUtil.result(BusinessExceptionEnum.SUCCESS, user);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户", response = HttpMessage.class)
    @DeleteMapping("/delete/{accountId}")
    public ResponseResult delete(@ApiParam(value = "用户accountId", required = true) @PathVariable("accountId") String accountId
    ) {

        UserDeleteDto userDeleteDto = new UserDeleteDto();
        userDeleteDto.setAccountId(accountId);
        LogUtil.printLog(logger, "删除用户 接口参数：{}", JSONObject.toJSONString(userDeleteDto));

        User user = userService.delete(userDeleteDto);
        return ResultUtil.result(BusinessExceptionEnum.SUCCESS, user);
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", response = HttpMessage.class)
    @PutMapping("/update/{accountId}")
    public ResponseResult update(@ApiParam(value = "用户accountId", required = true) @PathVariable("accountId") String accountId,
                                 @ApiParam(value = "更新信息", required = true) @RequestBody UserUpdateDto userUpdateDto
    ) {

        LogUtil.printLog(logger, "修改用户信息 接口参数：{} ，{}", accountId, JSONObject.toJSONString(userUpdateDto));

        User user = userService.update(accountId, userUpdateDto);
        return ResultUtil.result(BusinessExceptionEnum.SUCCESS, user);
    }

    @ApiOperation(value = "新增用户", notes = "新增用户", response = HttpMessage.class)
    @PostMapping("/add}")
    public ResponseResult add(@ApiParam(value = "新增用户信息", required = true) @RequestBody UserAddDto userAddDto
    ) {

        LogUtil.printLog(logger, "新增用户 接口参数：{}", JSONObject.toJSONString(userAddDto));

        User user = userService.add(userAddDto);
        return ResultUtil.result(BusinessExceptionEnum.SUCCESS, user);
    }

}
