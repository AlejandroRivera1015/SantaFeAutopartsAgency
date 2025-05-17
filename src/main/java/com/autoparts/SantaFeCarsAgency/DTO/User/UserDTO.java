package com.autoparts.SantaFeCarsAgency.DTO.User;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private char role;
}
