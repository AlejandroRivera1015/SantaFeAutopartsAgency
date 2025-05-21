package com.autoparts.SantaFeCarsAgency.Entity;
import com.autoparts.SantaFeCarsAgency.DTO.Item.Item;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<Item> items;
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "order_id")
    private Order order;


}
