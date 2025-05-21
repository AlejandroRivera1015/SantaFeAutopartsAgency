package com.autoparts.SantaFeCarsAgency.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  name;
    private String email;
    private String password;
    private String phone;
    private String address;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orders;
    private char role;


    public  User(String email, String password){
        this.email = email;
        this.password = password;
    }


    public  User(String email, String password, Character role ){
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
