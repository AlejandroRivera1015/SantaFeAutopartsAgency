package com.autoparts.SantaFeCarsAgency.Service.User;

import com.autoparts.SantaFeCarsAgency.DTO.User.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public UserDTO login(String email, String password);
}
