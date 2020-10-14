package com.wywk.myautotest.service;

/**
 * @ClassName: RedisService
 * @Author:  zsj
 * @Since:  2020/7/3 13:55
 * @Description:
 *
 * redis操作Service,
 * 对象和数组都以json形式进行存储
 *
 */
public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    Object get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    boolean remove(String... key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);

    /**
     * map hashSet
     *
     */
    boolean hashSet(String key, String item, Object value);


    /**
     * map hashSet set time
     *
     */
    boolean hashSet(String key, String item, Object value, long time);


}

