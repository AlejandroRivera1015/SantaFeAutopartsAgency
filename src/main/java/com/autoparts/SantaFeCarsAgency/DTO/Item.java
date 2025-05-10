package com.autoparts.SantaFeCarsAgency.DTO;


import com.autoparts.SantaFeCarsAgency.Entity.Product;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Item {
    private Long productId;
    private Long quantity;
}
