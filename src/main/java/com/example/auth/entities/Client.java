package com.example.auth.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long officeId;
    private String firstname;
    private String lastname;
    private String MobileNumber;
    private String dateFormat;
    private String locale;
    private boolean active;


}
