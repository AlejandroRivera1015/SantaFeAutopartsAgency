package com.autoparts.SantaFeCarsAgency.DTO.Item;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
@ToString
public class Item {
    private Long productId;
    private Long quantity;
}
