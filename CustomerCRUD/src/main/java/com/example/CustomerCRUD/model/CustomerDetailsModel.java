package com.example.CustomerCRUD.model;

import com.example.CustomerCRUD.Entity.CustomerDetails;
import lombok.Data;

@Data
public class CustomerDetailsModel {
    private Long id;


    private CustomerDetails.BankDetails bankDetails;


    private CustomerDetails.PersonalDetails personalDetails;
    private String accountNumber;
    private String ifscCode;
    private String bankName;
    private String dob;
    private String gender;
    private String phoneNumber;
    private String email;
}
