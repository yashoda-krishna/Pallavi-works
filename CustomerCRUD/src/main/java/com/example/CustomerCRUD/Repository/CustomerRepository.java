package com.example.CustomerCRUD.Repository;




import com.example.CustomerCRUD.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface CustomerRepository extends JpaRepository<Customer, Long> { }


