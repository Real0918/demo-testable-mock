package com.example.demo.modular.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.modular.entity.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/01/13 11:26:32
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据name查询list
     *
     * @param name
     * @return
     */
    List<User> selectListByName(@Param("name") String name);

}
