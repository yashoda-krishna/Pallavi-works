package com.example.jdbc.Repository;




import com.example.jdbc.Entity.Customer;

import java.util.List;
import java.util.Optional;

    public interface CustomerRepository {
        int save(Customer customer);
        int update(Customer customer);
        int deleteById(Long id);
        Optional<Customer> findById(Long id);
        List<Customer> findAll();
    }


