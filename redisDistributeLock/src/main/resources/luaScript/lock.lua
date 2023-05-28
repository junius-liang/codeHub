"if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 then " +
                        "redis.call('hincrby',KEYS[1],ARGV[1],1) " +
                        "redis.call('expire',KEYS[1],ARGV[2]) " +
                        "return 1 " +
                "else " +
                        "return 0 " +
                "end"

private String lockName;//KEYS[1]--JuniusRedisLock
    private String uuidValue;//ARGV[1]
    private long   expireTime;//ARGV[2]

