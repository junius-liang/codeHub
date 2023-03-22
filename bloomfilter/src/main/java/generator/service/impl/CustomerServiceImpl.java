package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.bloomfilterImpl.BloomFilterInit;
import generator.bloomfilterImpl.CheckUtil;
import generator.mapper.CustomerMapper;
import generator.model.Customer;
import generator.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
*
*/
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
implements CustomerService{
    public static final String KEY = "CUSTOMER_KEY_";
    @Resource
    private CustomerMapper customerMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private CheckUtil checkUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public void add(Customer customer) {
        int res = customerMapper.insert(customer);
        if (res>0){
            Customer c = customerMapper.selectById(customer.getId());
            String key = KEY+c.getId();
            redisTemplate.opsForValue().set(key,c);
        }

    }

    @Override
    public Customer get(Integer id) {
        String key = KEY+id;
        Customer result = null;
        //查询布隆过滤器
        if (!checkUtil.checkInWhiteListCustomer(key)){
            LOGGER.info("白名单无此用户,禁止访问 "+key);
            return null;
        }
        //查询缓存
        Object o = redisTemplate.opsForValue().get(key);
        //缓存不为空直接返回
       if (o!=null){
           result = (Customer)o;
       }else{
           synchronized (CustomerServiceImpl.class){
            o = redisTemplate.opsForValue().get(key);
            if (o==null){
                //缓存为空则去数据库查询
                result = customerMapper.selectById(id);
                //如果customer存在，则将数据存入缓存
                if (result!=null){
                    redisTemplate.opsForValue().set(key,result);
                }
            }else {
                result = (Customer)o;
            }
           }
       }
        return result;
    }
}
