package com.autoparts.SantaFeCarsAgency.DTO.Order;

import com.autoparts.SantaFeCarsAgency.DTO.User.UserDTO;
import com.autoparts.SantaFeCarsAgency.Entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class OrderDTO {
    private UserDTO user;

}
