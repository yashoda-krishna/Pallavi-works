package com.example.JPA.Entity;


    import jakarta.persistence.*;
    import lombok.Data;

@Entity
    @Data
    @Table(name = "customers")
    public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String email;
        private String status;
        private String payment;

        // Getters and Setters
    }


