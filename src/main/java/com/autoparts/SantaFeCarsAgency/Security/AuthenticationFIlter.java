package com.autoparts.SantaFeCarsAgency.Security;

import com.autoparts.SantaFeCarsAgency.Security.Utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class AuthenticationFIlter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    protected void  doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
        try{
            if (request.getRequestURI().contains("/auth/")) {
                filterChain.doFilter(request, response);
                return;
            }
            else {
                Cookie[] cookies = request.getCookies();
                Arrays.stream(cookies).forEach(cookie -> {
                    if(cookie.getName().equals("JWTID")){
                        Authentication auth = null;
                        String jwtID = cookie.getValue();
                        Jws<Claims> claimsJws = jwtUtil.validateToken(jwtID);
                        if(claimsJws !=null && !claimsJws.getBody().isEmpty()){
                            auth = getAuthentication(claimsJws.getBody());
                            SecurityContextHolder.getContext().setAuthentication(auth);
                            return;
                        }
                    }
                });

            }
            filterChain.doFilter(request, response);

        }
        catch (Exception e){
            System.out.println("JwtAuthValidatorFilter: Error validating token" + e.getMessage());


        }

    }

    private Authentication getAuthentication(Claims claims ){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(claims.get("role").toString()));
        UserDetails userDetails = new User(claims.get("id").toString(),"", new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(userDetails,"", grantedAuthorities);

    }

}
