package com.autoparts.SantaFeCarsAgency.DTO.Order;

import com.autoparts.SantaFeCarsAgency.DTO.Item.Item;
import com.autoparts.SantaFeCarsAgency.DTO.User.UserDTO;
import com.autoparts.SantaFeCarsAgency.Entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter
public class OrderDTO {
    private UserDTO user;
    private List<Item> cart;

}
