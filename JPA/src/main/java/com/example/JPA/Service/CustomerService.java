package com.example.JPA.Service;


    import com.example.JPA.Entity.Customer;

    import java.util.List;

    public interface CustomerService {
        Customer createCustomer(Customer c);
        Customer getCustomer(Long id);
        List<Customer> getAllCustomers();
        int updateCustomer(Long id, Customer c);
        int deleteCustomer(Long id);
    }


