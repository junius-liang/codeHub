package com.app.service;

import cn.hutool.core.util.IdUtil;
import com.app.lock.LockFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author junius
 * @date 2023/03/30 13:24
 * @project codeHub
 **/
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;

    @Autowired
    private LockFactory lockFactory;

    // JVM级别的锁
    public String redisLock0()
    {
        Lock lock = new ReentrantLock();
        String retMessage = "";
        lock.lock();
        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }finally {
            lock.unlock();
        }
        return retMessage+"\t"+"服务端口号："+port;
    }

    /**
     * Redis分布式锁1.0
     * 添加锁的重试功能
     * 说明：基本的功能以及重试可以实现，但是在超高并发的环境下递归重试容易导致stackoverflow，需要进一步完善
     */
    public void redisLock1(){
        String retMessage = "";
        //Redis锁的键名
        final String lockName = "JuniusRedisLock";
        //持有锁对象的唯一id
        String curThreadId = IdUtil.simpleUUID()+"::"+Thread.currentThread().getId();
        //尝试获取锁
        Boolean isGetLock = stringRedisTemplate.opsForValue().setIfAbsent(lockName, curThreadId);
        //如果没有获取到锁就休眠后重试获得锁，获取到则执行业务并在业务完成后删除锁
        if (!isGetLock){
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redisLock1();
        }else {
            try {
                //1 查询库存信息
                String result = stringRedisTemplate.opsForValue().get("inventory001");
                //2 判断库存是否足够
                Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
                //3 扣减库存
                if(inventoryNumber > 0) {
                    stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                    retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                    System.out.println(retMessage);
                }else{
                    retMessage = "商品卖完了，o(╥﹏╥)o";
                }
            }finally {
                stringRedisTemplate.delete(lockName);
            }
        }

    }

    /**
     * Redis分布式锁2.0
     * 改善锁的重试功能
     * 说明：使用自旋的方式来代替递归重试
     */
    public void redisLock2(){
        String retMessage = "";
        //Redis锁的键名
        final String lockName = "JuniusRedisLock";
        //持有锁对象的唯一id
        String curThreadId = IdUtil.simpleUUID()+"::"+Thread.currentThread().getId();
        //采用自旋的方式来获取锁
        while (Boolean.FALSE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockName, curThreadId))){
            try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
        }
        //获取到锁后执行业务逻辑最后删除锁
        try {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
                System.out.println(retMessage);
            }
        }finally {
            stringRedisTemplate.delete(lockName);
        }

    }

    /**
     * Redis分布式锁3.0
     * 防止宕机后发生死锁，必须设置过期时间
     * 说明：使用自旋的方式来代替递归重试
     */
    public void redisLock3(){
        String retMessage = "";
        //Redis锁的键名
        final String lockName = "JuniusRedisLock";
        //持有锁对象的唯一id
        String curThreadId = IdUtil.simpleUUID()+"::"+Thread.currentThread().getId();
        //采用自旋的方式来获取锁,获取锁时必须设置过期时间，避免发生死锁
        while (Boolean.FALSE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockName, curThreadId,5,TimeUnit.MINUTES))){
            try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
        }
        //获取到锁后执行业务逻辑最后删除锁
        try {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
                System.out.println(retMessage);
            }
        }finally {
            stringRedisTemplate.delete(lockName);
        }
    }

    /**
     * 当业务处理时间超过了默认key的过期时间，需要防止锁的误删
     */
    public void redisLock4() {
        String retMessage = "";
        //Redis锁的键名
        final String lockName = "JuniusRedisLock";
        //持有锁对象的唯一id
        String curThreadId = IdUtil.simpleUUID()+"::"+Thread.currentThread().getId();
        //采用自旋的方式来获取锁,获取锁时必须设置过期时间，避免发生死锁
        while (Boolean.FALSE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockName, curThreadId,5,TimeUnit.MINUTES))){
            try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
        }
        //获取到锁后执行业务逻辑最后删除锁
        try {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
                System.out.println(retMessage);
            }
        }finally {
            if (Objects.requireNonNull(stringRedisTemplate.opsForValue().get(lockName)).equalsIgnoreCase(curThreadId)){
                stringRedisTemplate.delete(lockName);
            }
        }

    }

    /**
     * 由于判断删除key的两个操作不是原子操作，需要用lua脚本把他们变为原子操作
     */
    public void redisLock5() {
        String retMessage = "";
        //Redis锁的键名
        final String lockName = "JuniusRedisLock";
        //持有锁对象的唯一id
        String curThreadId = IdUtil.simpleUUID()+"::"+Thread.currentThread().getId();
        //采用自旋的方式来获取锁,获取锁时必须设置过期时间，避免发生死锁
        while (Boolean.FALSE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockName, curThreadId,5,TimeUnit.MINUTES))){
            try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
        }
        //获取到锁后执行业务逻辑最后删除锁
        try {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
                System.out.println(retMessage);
            }

        }finally {
//            使用lua脚本变为原子操作
//            if (Objects.requireNonNull(stringRedisTemplate.opsForValue().get(lockName)).equalsIgnoreCase(curThreadId)){
//                stringRedisTemplate.delete(lockName);

            String luaScript =
                    "if (redis.call('get',KEYS[1]) == ARGV[1]) then " +
                            "return redis.call('del',KEYS[1]) " +
                            "else " +
                            "return 0 " +
                            "end";
            stringRedisTemplate.execute(new DefaultRedisScript<>(luaScript,Boolean.class), Arrays.asList(lockName),curThreadId);
        }
    }

    /**
     * 可重入锁+设计模式
     */
    public void redisLock6(){
        Lock lock = lockFactory.getLockByFactory("redis");
        String retMessage = "";
        lock.lock();
        try
        {
            lock.lock();
            System.out.println("-------------------");
            lock.unlock();
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }

        }finally {
            lock.unlock();
        }

    }

    /**
     * 过期时间的自动续约
     */
    public void redisLock7(){

    }

}




