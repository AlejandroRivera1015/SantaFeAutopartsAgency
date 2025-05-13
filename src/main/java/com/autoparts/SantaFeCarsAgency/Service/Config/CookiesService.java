package com.autoparts.SantaFeCarsAgency.Service.Config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookiesService {
    public  void setJWTCookie(String cookieValue, HttpServletResponse response ){

        Cookie jwtCookie = new Cookie("JWTID", cookieValue);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setAttribute("SameSite", "Lax");
        jwtCookie.setSecure( false); // Set to true if using HTTPS
        jwtCookie.setMaxAge(3600 * 24);
        response.addCookie(jwtCookie);
    }
}
