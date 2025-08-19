package com.example.jdbc.Service;




import com.example.jdbc.Entity.Customer;
import com.example.jdbc.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    public class CustomerServiceImpl implements CustomerService {

        @Autowired
        private CustomerRepository repository;

        @Override
        public int createCustomer(Customer customer) {
            return repository.save(customer);
        }

        @Override
        public int updateCustomer(Customer customer) {
            return repository.update(customer);
        }

        @Override
        public int deleteCustomer(Long id) {
            return repository.deleteById(id);
        }

        @Override
        public Optional<Customer> getCustomerById(Long id) {
            return repository.findById(id);
        }

        @Override
        public List<Customer> getAllCustomers() {
            return repository.findAll();
        }
    }


