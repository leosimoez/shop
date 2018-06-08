package br.com.lm.shop.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.lm.shop.bo.CartBO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtCartServiceTest {
	
	@Autowired
	JwtCartService service;
	

//	@Test
	public void testJwtCartService() {
		fail("Not yet implemented");
	}

	@Test
	public void testEncode() throws JsonProcessingException {
		CartBO bo = CartBO.builder()
				.zipCode("99999")
				.build();
		
		System.out.println(service.encode(bo));
		
		
	}

	@Test
	public void testDecode() throws JsonParseException, JsonMappingException, IOException {
		CartBO bo = service.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ6aXBDb2RlXCI6XCI5OTk5OVwiLFwiaXRlbXNcIjpudWxsLFwic2hpcENvc3RcIjpudWxsLFwidG90YWxcIjowfSIsImV4cCI6MTUyODE3NTAzN30.lYwkGdH9lhaVqeiBWYvRWMw_ZhUa9tsIhi5CZtKZJ3c");
		System.out.println(bo);
	}

}
