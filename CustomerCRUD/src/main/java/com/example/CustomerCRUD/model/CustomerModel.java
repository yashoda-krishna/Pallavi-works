package com.example.CustomerCRUD.model;

import com.example.CustomerCRUD.Entity.Address;
import com.example.CustomerCRUD.Entity.CustomerDetails;
import lombok.Data;

@Data
public class CustomerModel {
    private Long id;

    private String customerName;
    private CustomerDetails customerDetails;
    private Address address;

}
