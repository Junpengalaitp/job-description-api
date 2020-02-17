package com.alaitp.jobdescriptionapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RedisServiceImpl {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    public static final long NOT_EXPIRE = -1;

    public Boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }


    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }


    public Boolean renameKeyNotExist(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }


    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }


    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }


    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }


    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }


    public void expireKeyAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }


    public Long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }


    public void persistKey(String key) {
        redisTemplate.persist(key);
    }
}


