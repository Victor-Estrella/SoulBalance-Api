package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

	private static final String SECRET = "suaChaveSecretaSuperSecreta1234567890";
	private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

	public String generateToken(UsuarioEntity usuario) {
		return Jwts.builder()
				.setSubject(usuario.getEmail())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(KEY, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(KEY)
				.setAllowedClockSkewSeconds(300) // permite até 5 minutos de diferença
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public boolean isTokenValid(String token, UsuarioEntity usuario) {
	    final String username = extractUsername(token);
	    return (username.equals(usuario.getEmail()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		final Date expiration = Jwts.parserBuilder()
				.setSigningKey(KEY)
				.setAllowedClockSkewSeconds(300) // permite até 5 minutos de diferença
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
		return expiration.before(new Date());
	}

}
