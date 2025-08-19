package com.example.CustomerCRUD.model;

import lombok.Data;

@Data
public class AddressModel {
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
