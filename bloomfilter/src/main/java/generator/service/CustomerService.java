package generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import generator.model.Customer;

/**
*
*/
public interface CustomerService extends IService<Customer> {
    public void add(Customer customer);

    public Customer get(Integer id);
}
