package com.example.CustomerCRUD.Service;




import com.example.CustomerCRUD.Entity.Customer;
import com.example.CustomerCRUD.Exception.NotFoundException;
import com.example.CustomerCRUD.Repository.CustomerRepository;
import com.example.CustomerCRUD.model.CustomerDetailsModel;
import com.example.CustomerCRUD.model.CustomerModel;
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

    public Customer update(Long id, CustomerModel payload) {
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


