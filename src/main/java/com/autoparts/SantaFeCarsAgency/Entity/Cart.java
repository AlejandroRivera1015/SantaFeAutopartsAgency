package com.autoparts.SantaFeCarsAgency.Entity;
import com.autoparts.SantaFeCarsAgency.DTO.Item.Item;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    private List<Item> items;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;


}
