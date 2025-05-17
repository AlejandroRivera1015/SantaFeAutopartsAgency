package com.autoparts.SantaFeCarsAgency.DTO.User;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String name;
    private char role;
}
