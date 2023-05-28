package com.app.redisDistributedLock;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author junius
 * @date 2023/03/31 15:56
 * @project codeHub
 **/
public class RedisDistributedLock implements Lock , java.io.Serializable {

    /**
     * 锁的名称
     */
    private String lockName;

    /**
     * 持有锁的唯一id
     */
    private String id;

    /**
     * redis
     */
    private StringRedisTemplate redisTemplate;

    /**
     * 本地变量副本
     */
    private ThreadLocal<String> localId = ThreadLocal.withInitial(() -> "");




    @Override
    public void lock() {
        tryLock();
    }

    @Override
    public boolean tryLock() {
        boolean res = false;
        try {
            res = tryLock(-1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (time!=-1L){

        }
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }
}
