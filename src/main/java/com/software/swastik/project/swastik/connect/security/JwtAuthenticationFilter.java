package com.software.swastik.project.swastik.connect.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        //Get Token
        String requestToken = request.getHeader("Authorization");                //Returns Bearer <Token>
        String userName = null;
        String token = null;

        if(requestToken != null && requestToken.startsWith("Bearer")) {
            token        = requestToken.substring(7);
            try{
                userName = this.jwtTokenHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException e){
                System.out.println("Unable to get Jwt Token");
            }catch (ExpiredJwtException e){
                System.out.println("Jwt Token has expired !!! Please re-generate token");
            }catch (MalformedJwtException e){
                System.out.println("Invalid Jwt Token : " + token);
            }

        }else {
            System.out.println("Jwt Token doesn't starts with Bearer !!! ");
        }

        //Once Token Is Generated, We need to validate Token
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails =  this.userDetailsService.loadUserByUsername(userName);
            if(this.jwtTokenHelper.validateToken(token, userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else{
                System.out.println("Invalid Jwt Token : " + token);
            }

        }else{
            System.out.println("User Name is Null or Context is Not Null");
        }

        filterChain.doFilter(request, response);
    }
}
