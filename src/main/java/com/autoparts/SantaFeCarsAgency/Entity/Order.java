package com.autoparts.SantaFeCarsAgency.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "customer_order")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"name", "email","password","phone","address","orders","role"})
    private User user;
    @JsonManagedReference
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Cart  cart;
    private Double totalPrice;
    private Boolean isPaid;
    private Boolean isDelivered;
    private Boolean isReady;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String deliveryAddress;
    private  String OrderStatus;
}
