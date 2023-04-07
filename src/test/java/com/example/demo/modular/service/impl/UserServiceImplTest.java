package com.example.demo.modular.service.impl;

import com.alibaba.testable.core.annotation.MockMethod;
import com.alibaba.testable.core.matcher.InvokeVerifier;
import com.alibaba.testable.core.tool.OmniConstructor;
import com.alibaba.testable.core.tool.PrivateAccessor;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.BusinessExceptionEnum;
import com.example.demo.exception.IResultCode;
import com.example.demo.modular.dao.UserMapper;
import com.example.demo.modular.entity.dto.*;
import com.example.demo.modular.entity.po.User;
import com.example.demo.utils.Asserts;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.alibaba.testable.core.tool.TestableTool.MOCK_CONTEXT;

/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/4/15 14:33:44
 */
public class UserServiceImplTest {
    // 被测试类
    private UserServiceImpl userService = new UserServiceImpl();

    @Rule
    public ExpectedException exceptedEx = ExpectedException.none();

    public static class Mock {

//        @MockConstructor
//        private String createString() {
//            return "string";
//        }

        // mock 私有方法
        @MockMethod(targetClass = UserServiceImpl.class, targetMethod = "getUserByAccountId")
        private User getUser(String accountId) {
            // 同一方法被调用多次，需返回不同结果
            // 通过全局Map中不同参数，走不同逻辑
            String userCase = (String) MOCK_CONTEXT.get("getUser");
            if (Objects.isNull(userCase)) {
                // 默认情况
                User user = OmniConstructor.newInstance(User.class);
                user.setId(1);
                user.setName("test");
                return user;
            }
            switch (userCase) {
                case "aUser":
                    User user = OmniConstructor.newInstance(User.class);
                    user.setId(1);
                    user.setName("test");
                    return user;
                case "null":
                    return null;
                default:
                    return null;
            }
        }

        // mock 返回值void方法
        @MockMethod(targetClass = UserServiceImpl.class, targetMethod = "validate")
        private void validate(Object obj) {
            System.out.println("validate test");
        }

        // mock 静态方法
        @MockMethod(targetClass = Asserts.class, targetMethod = "notNull")
        private void notNull(Object obj, IResultCode resultCode) {
            if (Objects.isNull(obj)) {
                throw new BusinessException(resultCode.getCode(), resultCode.getMessage());
            }
        }

        // 拦截System.out.println调用
        @MockMethod
        private void println(PrintStream self, String msg) {
            self.println(msg);
        }

        // mock dao中自己写的方法
        @MockMethod(targetClass = UserMapper.class)
        private List<User> selectListByName(String name) {
            List<User> userList = Arrays.asList(OmniConstructor.newArray(User.class, 3));
            return userList;
        }

    }

    @Before
    public void before() {
        System.out.println("---------------------------------- before ----------------------------------");
    }

    @After
    public void after() {
        System.out.println("---------------------------------- after ----------------------------------");
    }

    /**
     * 测试：正常流程
     * 预期：返回正常结果数据
     */
    @Test
    public void testList01() {
        System.out.println("---------------------------------- start ----------------------------------");
        System.out.println("testList01");

        // 方法参数
        UserListDto userListDto = new UserListDto();
        userListDto.setPageNumber(1);
        userListDto.setPageSize(1);

        // mock结果构建
        // OmniConstructor能够构建任何对象、数组（TeatableMock提供）
        // User user = OmniConstructor.newInstance(User.class);
        List<User> userList = Arrays.asList(OmniConstructor.newArray(User.class, 3));
        // mock
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        // 给测试类中私有对象(userMapper)赋值
        PrivateAccessor.set(userService, "userMapper", userMapper);
        Mockito.when(userMapper.selectPage(Mockito.any(), Mockito.any())).thenReturn(userList);

        // 方法调用
        Page<User> result = userService.list(userListDto);

        // 断言
        Assert.assertEquals(userList, result.getRecords());
        // validate() 是否调用1次
        InvokeVerifier.verify("validate").withTimes(1);

        System.out.println("---------------------------------- end ----------------------------------");
    }

    /**
     * 测试：正常流程
     * 预期：返回正常结果数据
     */
    @Test
    public void testDetail01() {
        System.out.println("---------------------------------- start ----------------------------------");
        System.out.println("testDetail01");

        // 参数构建
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setAccountId("111");

        // 全局Map中赋值不同，走不同逻辑
        MOCK_CONTEXT.put("getUser", "aUser");
        // 方法调用
        User result = userService.detail(userDetailDto);

        // 断言
        Assert.assertEquals("test", result.getName());

        // getUser() 是否调用1次
        InvokeVerifier.verify("getUser").withTimes(1);
        // getUser() 参数是否为 userDetailDto.getAccountId()
        InvokeVerifier.verify("getUser").with(userDetailDto.getAccountId());

        System.out.println("---------------------------------- end ----------------------------------");
    }

    /**
     * 测试：对象不存在异常流程
     * 预期：抛出 BusinessExceptionEnum.OBJECT_NOT_EXIST 异常结果
     */
    @Test
    public void testDetail02() {
        System.out.println("---------------------------------- start ----------------------------------");
        System.out.println("testDetail01");

        // 参数构建
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setAccountId("111");

        // 异常断言
        exceptedEx.expect(BusinessException.class);
        exceptedEx.expectMessage(BusinessExceptionEnum.OBJECT_NOT_EXIST.getMessage());

        MOCK_CONTEXT.put("getUser", "null");
        // 方法调用
        User result = userService.detail(userDetailDto);

        System.out.println("---------------------------------- end ----------------------------------");
    }

    /**
     * 测试：正常流程
     * 预期：返回正常结果数据
     */
    @Test
    public void testDelete01() {
        System.out.println("---------------------------------- start ----------------------------------");
        System.out.println("testDelete01");

        // 参数构建
        UserDeleteDto userDeleteDto = new UserDeleteDto();
        userDeleteDto.setAccountId("111");

        // mock
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        PrivateAccessor.set(userService, "userMapper", userMapper);
        Mockito.when(userMapper.updateById(Mockito.any())).thenReturn(1);

        // 方法调用
        User result = userService.delete(userDeleteDto);

        // 断言
        Assert.assertEquals("test", result.getName());
        InvokeVerifier.verify("getUser").withTimes(1);

        System.out.println("---------------------------------- end ----------------------------------");
    }

    /**
     * 测试：正常流程
     * 预期：返回正常结果数据
     */
    @Test
    public void testUpdate01() {
        System.out.println("---------------------------------- start ----------------------------------");
        System.out.println("testUpdate01");

        // 参数构建
        String accountId = "111";
        UserUpdateDto userUpdateDto = new UserUpdateDto();

        // mock
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        PrivateAccessor.set(userService, "userMapper", userMapper);
        Mockito.when(userMapper.updateById(Mockito.any())).thenReturn(1);

        // 方法调用
        User result = userService.update(accountId, userUpdateDto);

        // 断言
        Assert.assertEquals("test", result.getName());
        InvokeVerifier.verify("getUser").withTimes(2);

        System.out.println("---------------------------------- end ----------------------------------");
    }

    /**
     * 测试：正常流程
     * 预期：返回正常结果数据
     */
    @Test
    public void testAdd01() {
        System.out.println("---------------------------------- start ----------------------------------");
        System.out.println("testAdd01");

        // 参数构建
        UserAddDto userAddDto = new UserAddDto();

        // mock
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        PrivateAccessor.set(userService, "userMapper", userMapper);
        Mockito.when(userMapper.insert(Mockito.any())).thenReturn(1);

        // 方法调用
        User result = userService.add(userAddDto);

        // 断言
        Assert.assertEquals("test", result.getName());
        InvokeVerifier.verify("getUser").withTimes(1);

        System.out.println("---------------------------------- end ----------------------------------");
    }

    /**
     * 私有方法测试
     * 测试：正常流程
     * 预期：返回正常结果数据
     */
    @Test
    public void testGetUserByAccountId01() {
        System.out.println("---------------------------------- start ----------------------------------");
        System.out.println("testGetUserByAccountId01");

        // 参数构建
        String accountId = "111";

        // mock结果构建
        User[] array = OmniConstructor.newArray(User.class, 1);
        List<User> userList = Arrays.asList(array);
        // mock
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        PrivateAccessor.set(userService, "userMapper", userMapper);
        Mockito.when(userMapper.selectList(Mockito.any())).thenReturn(userList);

        // 私有方法调用
        User result = PrivateAccessor.invoke(userService, "getUserByAccountId", accountId);

        // 断言
        Assert.assertEquals(userList.get(0), result);

        System.out.println("---------------------------------- end ----------------------------------");
    }

    /**
     * 私有方法测试
     * 测试：正常流程
     * 预期：返回正常结果数据
     */
    @Test
    public void testGetListByAccountName01() {
        System.out.println("---------------------------------- start ----------------------------------");
        System.out.println("getListByAccountName");

        // 参数构建
        String name = "test";

        // 私有方法调用
        List<User> userList = PrivateAccessor.invoke(userService, "getListByAccountName", name);

        // 断言
        Assert.assertEquals(userList.size(), 3);

        System.out.println("---------------------------------- end ----------------------------------");
    }

    /**
     * 私有void方法测试
     * 测试：正常流程
     * 预期：返回正常结果数据
     */
    @Test
    public void testValidate01() {
        System.out.println("---------------------------------- start ----------------------------------");
        System.out.println("validate");

        // 参数构建
        Object paramObj = OmniConstructor.newInstance(Object.class);

        // 私有void方法调用
        PrivateAccessor.invoke(userService, "validate", paramObj);

        // 验证 方法println被指定参数"validate data"调用
        InvokeVerifier.verify("println").with("validate data");

        System.out.println("---------------------------------- end ----------------------------------");
    }

}
