package com.wywk.myautotest.service;

import com.wywk.myautotest.entity.User;

import java.util.List;

/**
 * @ClassName: UserService
 * @Author:  zsj
 * @Since: 2020/6/20 18:52
 * @Description:
 */

public interface UserService {

    /**
     * 查询所有用户
     * @return user
     */
    List<User> findAll();

    /**
     * 通过id查询用户
     * @param id
     * @return user
     */
    User findUserById(long id);

    boolean create(User user);

    /**
     * 更新用户
     * @param
     * @return bool
     */
    boolean update(User user);

    /**
     * 删除用户
     * @param id
     */
    boolean delete(long id);

    /**
     * 用户登录
     * @param name
     * @param pwd
     * @return bool token
     */
    boolean login(String name, String pwd);

    /**
     * 查询用户数据
     * @return
     */
    int userCount();

}
