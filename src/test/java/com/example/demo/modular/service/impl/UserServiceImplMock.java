//package com.example.demo.modular.service.impl;
//
//import com.alibaba.testable.core.annotation.MockMethod;
//import com.alibaba.testable.core.tool.OmniConstructor;
//import com.example.demo.exception.BusinessException;
//import com.example.demo.exception.IResultCode;
//import com.example.demo.modular.dao.UserMapper;
//import com.example.demo.modular.entity.po.User;
//import com.example.demo.utils.Asserts;
//
//import java.io.PrintStream;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//
//import static com.alibaba.testable.core.tool.TestableTool.MOCK_CONTEXT;
//
///**
// * @version 1.0
// * @author: WangWei
// * @date: 2021/8/2 11:00:58
// */
//public class UserServiceImplMock {
//
//    // mock 私有方法
//    @MockMethod(targetClass = UserServiceImpl.class, targetMethod = "getUserByAccountId")
//    private User getUser(String accountId) {
//        // 同一方法被调用多次，需返回不同结果
//        // 通过全局Map中不同参数，走不同逻辑
//        String userCase = (String) MOCK_CONTEXT.get("getUser");
//        if (Objects.isNull(userCase)) {
//            // 默认情况
//            User user = OmniConstructor.newInstance(User.class);
//            user.setId(1);
//            user.setName("test");
//            return user;
//        }
//        switch (userCase) {
//            case "aUser":
//                User user = OmniConstructor.newInstance(User.class);
//                user.setId(1);
//                user.setName("test");
//                return user;
//            case "null":
//                return null;
//            default:
//                return null;
//        }
//    }
//
//    // mock 返回值void方法
//    @MockMethod(targetClass = UserServiceImpl.class, targetMethod = "validate")
//    private void validate(Object obj) {
//        System.out.println("validate test");
//    }
//
//    // mock 静态方法
//    @MockMethod(targetClass = Asserts.class, targetMethod = "notNull")
//    private void notNull(Object obj, IResultCode resultCode) {
//        if (Objects.isNull(obj)) {
//            throw new BusinessException(resultCode.getCode(), resultCode.getMessage());
//        }
//    }
//
//    // 拦截System.out.println调用
//    @MockMethod
//    private void println(PrintStream self, String msg) {
//        self.println(msg);
//    }
//
//    // mock dao中自己写的方法
//    @MockMethod(targetClass = UserMapper.class)
//    private List<User> selectListByName(String name) {
//        List<User> userList = Arrays.asList(OmniConstructor.newArray(User.class, 3));
//        return userList;
//    }
//}
