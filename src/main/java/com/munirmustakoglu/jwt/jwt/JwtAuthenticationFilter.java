package com.munirmustakoglu.jwt.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {  //Bu sınıf sayesinde bu bir filter calsı olduğunu ve kullanıcı tarafından atılan bütün isteklerin controller katmanına gelmeden önce bu fşilter katmanına gelmesini sağlamak için

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header;
        String token;
        String username ;
       header = request.getHeader("Authorization");

       if (header != null ) {

           filterChain.doFilter(request, response);
           return;
       }
       token =header.substring(7);
       try {

           username =jwtService.getUserNameByToken(token);
           if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { //contex de herhangi bir token yoksa ve user name i başarıl bir şekilde bulaildiysen
              UserDetails userDetails= userDetailsService.loadUserByUsername(username);
              if (userDetails!=null && jwtService.isTokenExpired(token)) {
                  //user security context e koyulabilir.
                  UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                  authentication.setDetails(userDetails);

                  SecurityContextHolder.getContext().setAuthentication(authentication);
              }
           }

       }catch (ExpiredJwtException e) {
           System.out.println("Token süresi doldu"+e.getMessage());
       }
       catch (Exception e) {
           System.out.println("Genel bir hata oldu"+e.getMessage());
       }
       filterChain.doFilter(request, response);

    }
}
