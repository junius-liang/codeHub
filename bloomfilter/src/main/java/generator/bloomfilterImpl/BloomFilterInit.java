package generator.bloomfilterImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author junius
 * @date 2023/03/22 20:14
 * @project codeHub
 * 布隆过滤器白名单初始化工具类，一开始设置一部分数据为白名单所有
 * 白名单业务规定：布隆过滤器有白名单极可能有
 **/
@Component
public class BloomFilterInit {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(BloomFilterInit.class);


    public void init() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        //读取白名单配置文件
        Properties properties = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("whiteListCustomer.properties");
        properties.load(inputStream);
        properties.keySet().forEach(k-> {
            //白名单的Key
            String key = (String) properties.get(k);
            //获取哈希值
            int hashVal = Math.abs(key.hashCode());
            //通过哈希值对2^32取模得到槽位
            long index = (long) (hashVal % Math.pow(2, 32));
            LOGGER.info(key+":::::"+index);
            redisTemplate.opsForValue().setBit("WHITE_LIST_CUSTOMER",index,true);
        });
    }


    @PostConstruct
    public void init2(){
        new Thread(() -> {
            try {
                init();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("PostConstruct 执行完毕");
    }
}
