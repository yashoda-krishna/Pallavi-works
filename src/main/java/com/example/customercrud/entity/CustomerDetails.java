package com.example.customercrud.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private BankDetails bankDetails;

    @Embedded
    private PersonalDetails personalDetails;

    // Inner class for BankDetails
    @Embeddable
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class BankDetails {
        private String accountNumber;
        private String ifscCode;
        private String bankName;
    }

    // Inner class for PersonalDetails
    @Embeddable
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class PersonalDetails {
        private String dob;        // ISO-8601 date as string for simplicity
        private String gender;
        private String phoneNumber;
        private String email;
    }
}
