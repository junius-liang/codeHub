package generator.controller;

import generator.bloomfilterImpl.BloomFilterInit;
import generator.model.Customer;
import generator.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author junius
 * @date 2023/03/22 19:57
 * @project codeHub
 **/
@RestController
public class CustomerController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    BloomFilterInit bloomFilterInit;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody Customer customer){
        customerService.add(customer);
    }

    @GetMapping("/findCustomer")
    public Customer findCustomer(@RequestParam("id") Integer id){
        return customerService.get(id);
    }

    @GetMapping("/init")
    public void initCustomer() throws IOException {
        bloomFilterInit.init();
    }

}
