package br.com.lm.shop.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lm.shop.bo.CartBO;

@Service
public class JwtCartService {

	Algorithm algorithm;

	private static final String SECRET = "JwtCart00";

	private static final Integer TIME_LIMIT = 120;// 2 minutos

	@Autowired
	private ObjectMapper objectMapper;

	public JwtCartService() throws IllegalArgumentException, UnsupportedEncodingException {
		algorithm = Algorithm.HMAC256(SECRET);
	}

	public String encode(CartBO cart) throws JsonProcessingException {
		String payload = objectMapper.writeValueAsString(cart);
		String token = JWT.create().withSubject(payload)
				.withExpiresAt(
						Date.from(LocalDateTime.now().plusSeconds(120).atZone(ZoneId.systemDefault()).toInstant()))
				.sign(algorithm);

		return token;

	}

	public CartBO decode(String token) throws JsonParseException, JsonMappingException, IOException {
		JWTVerifier verifier = JWT.require(algorithm).build();

		try {
			DecodedJWT jwt = verifier.verify(token);
			// decode(token);
			String payload = jwt.getSubject();
			System.out.println("payload: " + payload);
			return objectMapper.readValue(payload, CartBO.class);
		} catch (JWTDecodeException exception) {
			exception.printStackTrace();
			return null;
		}
	}

}
