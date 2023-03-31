package com.app.lock;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

/**
 * @author junius
 * @date 2023/03/31 12:36
 * @project codeHub
 **/
@Component
public class LockFactory {
    private String uuIdValue ;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private final String lockName = "JuniusRedisLock";


    public LockFactory(){
        this.uuIdValue = IdUtil.randomUUID()+Thread.currentThread()+System.currentTimeMillis();
    }

    public  Lock getLockByFactory(String lockType){
        if ("redis".equalsIgnoreCase(lockType)){
            return new RedisDistributedLock(stringRedisTemplate,lockName);
        }
        return null;
    }
}
