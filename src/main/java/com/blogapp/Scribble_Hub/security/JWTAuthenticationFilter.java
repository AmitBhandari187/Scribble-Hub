package com.blogapp.Scribble_Hub.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private UserDetailsService userDetailsService;
    private JWTTokenHelper jwtTokenHelper;

    public  JWTAuthenticationFilter(UserDetailsService userDetailsService,JWTTokenHelper jwtTokenHelper){
        this.userDetailsService=userDetailsService;
        this.jwtTokenHelper=jwtTokenHelper;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1. Get Token
        String requestToken=request.getHeader("Authorization");
        System.out.println(requestToken);

        //Bearer 36478392

        String username=null;
        String token=null;

        if (requestToken != null && requestToken.startsWith("Bearer")){
            token=requestToken.substring(7);
            try {
                username=jwtTokenHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException e){
                System.out.println("Unable to get JWT token");
            }catch (ExpiredJwtException e){
                System.out.println("JWT token has expired");
            }catch (MalformedJwtException e){
                System.out.println("invalid JWT ");
            }
        }else {
            System.out.println("JWT token does not starts with Bearer");
        }

        // once token received we should validate the token
        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
            if (this.jwtTokenHelper.validateToken(token,userDetails.getUsername())){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }else{
                System.out.println("Invalid jwt token !!");
            }
        }else {
            System.out.println("Username or context something is not null !!");
        }

        filterChain.doFilter(request,response);

    }
}
