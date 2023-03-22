package generator.bloomfilterImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author junius
 * @date 2023/03/22 21:00
 * @project codeHub
 **/
@Component
public class CheckUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckUtil.class);
    public static final String BIT_KEY = "WHITE_LIST_CUSTOMER";

    /**
     * 判断Key是否在白名单中
     * @param key
     * @return
     */
    public boolean checkInWhiteListCustomer(String key){
        //获取哈希值
        int hashVal = Math.abs(key.hashCode());
        //通过哈希值对2^32取模得到槽位
        long index = (long) (hashVal % Math.pow(2, 32));
        Boolean isOwn = redisTemplate.opsForValue().getBit(BIT_KEY, index);
        return isOwn;
    }
}
