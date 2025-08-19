package com.example.jdbc.Controller;




import com.example.jdbc.Entity.Customer;
import com.example.jdbc.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/customers")
    public class JdbcController {

        @Autowired
        private CustomerService service;

        @PostMapping
        public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
            service.createCustomer(customer);
            return ResponseEntity.ok("Customer created successfully");
        }

        @PutMapping("/{id}")
        public ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
            customer.setId(id);
            service.updateCustomer(customer);
            return ResponseEntity.ok("Customer updated successfully");
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
            service.deleteCustomer(id);
            return ResponseEntity.ok("Customer deleted successfully");
        }

        @GetMapping("/{id}")
        public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
            return service.getCustomerById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping
        public ResponseEntity<List<Customer>> getAllCustomers() {
            return ResponseEntity.ok(service.getAllCustomers());
        }
    }


