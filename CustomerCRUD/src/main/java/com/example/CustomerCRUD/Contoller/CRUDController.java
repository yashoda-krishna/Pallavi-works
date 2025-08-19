package com.example.CustomerCRUD.Contoller;




import com.example.CustomerCRUD.Entity.Customer;
import com.example.CustomerCRUD.Service.CustomerService;
import com.example.CustomerCRUD.model.CustomerDetailsModel;
import com.example.CustomerCRUD.model.CustomerModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CRUDController {

    private final CustomerService service;

    public CRUDController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer) {
        return service.create(customer);
    }

    @GetMapping
    public List<Customer> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Customer one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody CustomerModel customer) {
        return service.update(id,  customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}



