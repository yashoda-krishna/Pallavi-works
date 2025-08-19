package com.example.JPA.Service;

 import com.example.JPA.Entity.Customer;
 import com.example.JPA.Repository.CustomerRepository;
 import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public Customer createCustomer(Customer c) {
        return repo.save(c);
    }

    @Override
    public Customer getCustomer(Long id) {
        Customer customer = repo.findByIdNative(id);
        if (customer == null) {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repo.findAllNative();
    }

    @Override
    public int updateCustomer(Long id, Customer c) {
        int updated = repo.updateCustomer(id, c.getName(), c.getEmail(), c.getStatus());
        if (updated == 0) {
            throw new RuntimeException("Update failed, no customer with ID: " + id);
        }
        return updated;
    }

    @Override
    public int deleteCustomer(Long id) {
        int deleted = repo.deleteByIdNative(id);
        if (deleted == 0) {
            throw new RuntimeException("Delete failed, no customer with ID: " + id);
        }
        return deleted;
    }
}

