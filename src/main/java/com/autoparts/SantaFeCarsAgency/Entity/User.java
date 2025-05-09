package com.autoparts.SantaFeCarsAgency.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class User {
    private String  name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Double numbe;

}
