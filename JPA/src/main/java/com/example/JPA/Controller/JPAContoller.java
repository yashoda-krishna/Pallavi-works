package com.example.JPA.Controller;


    import com.example.JPA.Entity.Customer;
    import com.example.JPA.Service.CustomerService;
    import org.springframework.web.bind.annotation.*;
    import java.util.List;

    @RestController
    @RequestMapping("/api/customers")

    public class JPAContoller {
        private final CustomerService service;

        public JPAContoller(CustomerService service) {
            this.service = service;
        }

        @PostMapping
        public Customer create(@RequestBody Customer c) {
            return service.createCustomer(c);
        }

        @GetMapping("/{id}")
        public Customer getOne(@PathVariable Long id) {
            return service.getCustomer(id);
        }

        @GetMapping
        public List<Customer> getAll() {
            return service.getAllCustomers();
        }

        @PutMapping("/{id}")
        public String update(@PathVariable Long id, @RequestBody Customer c) {
            service.updateCustomer(id, c);
            return "Customer updated successfully!";
        }

        @DeleteMapping("/{id}")
        public String delete(@PathVariable Long id) {
            service.deleteCustomer(id);
            return "Customer deleted successfully!";
        }
    }


