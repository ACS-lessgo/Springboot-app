package com.acs.demo.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class validateJWT extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String strJWT = request.getHeader(ConfigConstants.JWT_HEADER);

        if(strJWT!=null){
            try {
                String email = provideJWT.getEmailFromJwtToken(strJWT);

                List<GrantedAuthority> authorityList = new ArrayList<>();

                Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,authorityList);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (Exception e){
                throw new BadCredentialsException("Invalid token");
            }
        }

        filterChain.doFilter(request,response);
    }
}
