package com.autoparts.SantaFeCarsAgency.DTO;
import  java.util.List;
import com.autoparts.SantaFeCarsAgency.DTO.Item.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@ToString
public class CartDTO {
    private List<Item> items;
}
