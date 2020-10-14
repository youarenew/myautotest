package com.wywk.myautotest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisUtil
 * @Author:  zsj
 * @Since: 2020/4/2 14:02
 * @Description:  redis
 */

public class RedisUtil {

    @Autowired
    public RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Qualifier("redisTemplate")
    protected RedisTemplate<Serializable, Serializable> redisTemplateSerializable;

    /**
     * 指定缓存失效时间
     *
     * @param key  String key
     * @param time seconds
     * @return bool
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key key
     * @return bool
     */
    public long getExpire(String key) {
        if (hasKey(key)){
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } else {
            return 0;
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key key
     * @return true
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个参数，也可以传入多个参数
     */
    @SuppressWarnings("unchecked")
    public boolean del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
            return true;
        }
        return false;
    }


    /**
     * 普通缓存获取
     *
     * @param key string key
     * @return value
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   key
     * @param value obj value
     * @return bool
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入，指定时长
     *
     * @param key   string key
     * @param value string value
     * @param time  seconds
     * @return bool
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //============================ Map =============================

    /**
     * HastGet
     *
     * @param key  string key, nonnull
     * @param item string item, nonnull
     * @return value
     */
    public Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * HashMapGet
     *
     * @param key string key
     * @return value, one and more
     */
    public Map<Object, Object> hashMapGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 向Hash表中放入数据，如果不存在将创建
     *
     * @param key   string key
     * @param item  item
     * @param value value
     * @return bool
     */
    public boolean hashSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向Hash表中放入数据，如果不存在将创建，创建时指定时间
     *
     * @param key   string key
     * @param item  item
     * @param value value
     * @return bool
     */
    public boolean hashSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入HashSet如果不存在则新建
     *
     * @param key
     * @param map
     * @return
     */
    public boolean hashMapSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入HashSet,设置时间，如果不存在则新建
     *
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean hashMapSet(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除Hash表中的值
     *
     * @param key  key
     * @param item item
     */
    public void HashDel(String key, String item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    //============================ Set =============================

    /**
     * Query for a value of type set
     *
     * @param key key
     * @return value
     */
    public Set<Object> setGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Query whether the value exists according to the key
     *
     * @param key   key
     * @param value value, one or more
     * @return bool
     */
    public boolean setHasKey(String key, String value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Put in a set type cache
     *
     * @param key    key
     * @param values valve, one or more
     * @return The number of successful operations
     */
    public long setSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除set值
     * @param key key
     * @param values value,values
     * @return count
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //============================ list =============================

    /**
     * 缓存list数据
     * @param key key
     * @param value value
     * @return bool
     */
    public boolean listSet(String key, Object value)
    {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 缓存list数据，指定时长
     * @param key key
     * @param value value
     * @param time seconds
     * @return bool
     */
    public boolean listSet(String key, Object value, long time)
    {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 缓存list数据
     * @param key key
     * @param value value
     * @return bool
     */
    public boolean listSet(String key, List<Object> value)
    {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 缓存list数据，指定时长
     * @param key key
     * @param value value
     * @return bool
     */
    public boolean listSet(String key, List<Object> value, long time)
    {
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            if (time>0) {
                expire(key,time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获取list缓存的内容
     * @param key key
     * @param start start
     * @param end end  0 ~ -1代表所有值
     * @return
     */
    public List<Object> listGet(String key, long start, long end)
    {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据索引修改list中某条数据
     * @param key key
     * @param index index
     * @param value value
     * @return bool
     */
    public boolean listUpdateIndex(String key, long index, Object value)
    {
        try {
            redisTemplate.opsForList().set(key,index,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long listRemove(String key, long count, Object value)
    {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // redisTemplateSerializable

    public long del(final byte[] key) {
        return (long) redisTemplateSerializable.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) {
                return connection.del(key);
            }
        });
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public byte[] get(final byte[] key) {
        return (byte[]) redisTemplateSerializable.execute(new RedisCallback() {
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key);
            }
        });
    }

    /**
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplateSerializable.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }


}
