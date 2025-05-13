package com.autoparts.SantaFeCarsAgency.Controller;

import com.autoparts.SantaFeCarsAgency.DTO.User.UserDTO;
import com.autoparts.SantaFeCarsAgency.Entity.User;
import com.autoparts.SantaFeCarsAgency.Security.Utils.JwtUtil;
import com.autoparts.SantaFeCarsAgency.Service.Config.CookiesService;
import com.autoparts.SantaFeCarsAgency.Service.User.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CookiesService cookiesService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> logIn(@RequestBody User user, HttpServletResponse response){
        UserDTO userResponse = userService.login(user.getEmail(), user.getPassword());

        if (userResponse != null  && userResponse.getId() != 0L ) {
            Map<String,String> claims = new HashMap<>();
            claims.put("id", userResponse.getId().toString());
            claims.put("name",userResponse.getName().toString());
            claims.put("role", String.valueOf(userResponse.getRole()));
            String jwtToken = jwtUtil.generateToken(claims);
            System.out.println("JWT Token: " + jwtToken);
            this.cookiesService.setJWTCookie(jwtToken, response);

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userResponse, HttpStatus.NOT_FOUND);
        }
    }
}
