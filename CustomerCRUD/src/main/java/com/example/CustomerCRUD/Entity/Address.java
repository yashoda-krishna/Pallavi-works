package com.example.CustomerCRUD.Entity;



import jakarta.persistence.*;
import lombok.*;

@Entity

@NoArgsConstructor @AllArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}


