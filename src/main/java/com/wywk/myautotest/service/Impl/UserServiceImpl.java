package com.wywk.myautotest.service.Impl;


import com.wywk.myautotest.entity.User;
import com.wywk.myautotest.mapper.UserMapper;
import com.wywk.myautotest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Author: zsj
 * @Since:  2020/6/20 19:02
 * @Description:
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean create(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        user.setUpdateTime(new Date());
        return !(userMapper.updateByPrimaryKey(user) == 0);
    }


    @Override
    public boolean delete(long id) {
        return !(userMapper.deleteByPrimaryKey(id) == 0);

    }

    @Override
    public boolean login(String name, String pwd) {
        return false;
    }

    @Override
    public int userCount() {
        return userMapper.selectCount();
    }
}
