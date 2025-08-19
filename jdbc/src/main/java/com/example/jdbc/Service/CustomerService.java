package com.example.jdbc.Service;




import com.example.jdbc.Entity.Customer;

import java.util.List;
import java.util.Optional;

    public interface CustomerService {
        int createCustomer(Customer customer);
        int updateCustomer(Customer customer);
        int deleteCustomer(Long id);
        Optional<Customer> getCustomerById(Long id);
        List<Customer> getAllCustomers();
    }


