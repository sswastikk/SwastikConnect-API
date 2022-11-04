package com.software.swastik.project.swastik.connect.security;

import com.software.swastik.project.swastik.connect.configurations.AppConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper
{
    //Retrieve User Name from Jwt Token
    public String getUsernameFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //Retrieve Expiration Date from Jwt Token
    public Date getExpirationTokenFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //For retrieving any information from token we will need secret key
    private Claims getAllClaimsFromToken(String token)
    {
        return Jwts.parser().setSigningKey(AppConstants.SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //Check if the Token has Expired
    private Boolean isTokenExpired(String token)
    {
        final Date expiration = getExpirationTokenFromToken(token);
        return expiration.before(new Date());
    }

    //Generate Token For User
    public String generateToken(UserDetails userDetails)
    {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject)
    {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + AppConstants.JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, AppConstants.SECRET_KEY).compact();
    }

    //Validate Token
    public Boolean validateToken(String token, UserDetails userDetails)
    {
        final String userName = getUsernameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
