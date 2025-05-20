package com.autoparts.SantaFeCarsAgency.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private  Float  price;
    private String seller;
    private  Long quantity;
    private  Float discount;
    private String description;
    private String imageUrl;
    private Boolean isAvailable;

    public Product(String name, String category, Float price, String seller, Long quantity, Float discount, String description, String imageUrl, Boolean isAvailable) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.seller = seller;
        this.quantity = quantity;
        this.discount = discount;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isAvailable = isAvailable;
    }


}
