package com.example.CustomerCRUD.Entity;




import jakarta.persistence.*;
import lombok.*;

    @Entity
   @Data
    @NoArgsConstructor @AllArgsConstructor
    public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String customerName;

        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "details_id", referencedColumnName = "id")
        private CustomerDetails customerDetails;

        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "address_id", referencedColumnName = "id")
        private Address address;
    }



