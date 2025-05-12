package com.autoparts.SantaFeCarsAgency.Controller;

import com.autoparts.SantaFeCarsAgency.DTO.User.UserDTO;
import com.autoparts.SantaFeCarsAgency.Entity.User;
import com.autoparts.SantaFeCarsAgency.Service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("login")
    public ResponseEntity<?> logIn(@RequestBody User user){
        UserDTO userResponse = userService.login(user.getEmail(), user.getPassword());

        if (userResponse != null  && userResponse.getId() != 0L ) {
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userResponse, HttpStatus.NOT_FOUND);
        }
    }
}
