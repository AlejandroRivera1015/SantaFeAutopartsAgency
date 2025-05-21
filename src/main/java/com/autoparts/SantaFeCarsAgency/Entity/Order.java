package com.autoparts.SantaFeCarsAgency.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "customer_order")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    private Cart  cart;
    private Double totalPrice;
    private Boolean isPaid;
    private Boolean isDelivered;
    private Boolean isReady;
    private String orderDate;
    private String deliveryDate;




}
