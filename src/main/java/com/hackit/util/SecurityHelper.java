package com.hackit.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.hackit.modal.Booking;

import org.springframework.stereotype.Component;import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@PropertySource("classpath:application.properties")
public class SecurityHelper {
    @Value("${hackit.jwt.secret}")
    private String JWT_SECRET;

    @Value("${hackit.jwt.validity.seconds}")
    private Integer JWT_TOKEN_VALIDITY;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
    //retrieve username from jwt token
	public String getBookingIdFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}	
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
    private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
	}	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}	//generate token for user
	public String generateToken(Booking booking) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, booking.getIdInStr());
	}	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string 
	private String doGenerateToken(Map<String, Object> claims, String subject) {	
			return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
				//.signWith(SignatureAlgorithm.RSA256, JWT_SECRET).compact();
	}	//validate token
	public Boolean validateToken(String token) {
		try{
			return (!isTokenExpired(token));
		}	
		catch (SignatureException e)
		{
			logger.debug("SignatureException");
			return false;
		}
		catch (ExpiredJwtException e)
		{
			logger.debug("ExpiredJwtException");
			return false;
		}

		
	}

}
// reference : https://medium.com/swlh/spring-boot-security-jwt-hello-world-example-b479e457664c