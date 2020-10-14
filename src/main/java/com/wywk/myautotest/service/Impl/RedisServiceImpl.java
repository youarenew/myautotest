package com.wywk.myautotest.service.Impl;

import com.wywk.myautotest.service.RedisService;
import com.wywk.myautotest.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @ClassName: RedisServiceImpl
 * @Author: zsj
 * @Since:  2020/7/3 13:57
 * @Description:
 */

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired(required = false)
    private RedisUtil redisUtil;

    @Override
    public void set(String key, String value) {
        redisUtil.set(key, value);
    }

    @Override
    public Object get(String key) {
        return redisUtil.get(key);
    }

    @Override
    public boolean expire(String key, long expire){
        return redisUtil.expire(key,expire);
    }

    @Override
    public boolean remove(String... key){
        return redisUtil.del(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return null;
    }

    @Override
    public boolean hashSet(String key, String item, Object value) {
        return redisUtil.hashSet(key, item, value);
    }

    @Override
    public boolean hashSet(String key, String item, Object value, long time) {
        return redisUtil.hashSet(key, item, value, time);
    }
}
