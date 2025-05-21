package com.autoparts.SantaFeCarsAgency.DTO.CartDTO;

import com.autoparts.SantaFeCarsAgency.DTO.Item.Item;
import com.autoparts.SantaFeCarsAgency.DTO.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CartDTO {
    private UserDTO user;
    private List<Item> items;
}
