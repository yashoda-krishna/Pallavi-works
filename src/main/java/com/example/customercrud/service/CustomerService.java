package com.example.customercrud.service;

import com.example.customercrud.entity.Customer;
import com.example.customercrud.exception.NotFoundException;
import com.example.customercrud.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found: " + id));
    }

    public Customer update(Long id, Customer payload) {
        Customer existing = findById(id);
        existing.setCustomerName(payload.getCustomerName());
        existing.setCustomerDetails(payload.getCustomerDetails());
        existing.setAddress(payload.getAddress());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Customer not found: " + id);
        }
        repository.deleteById(id);
    }
}
