package fr.pizzeria.admin.web.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenService {
	
	private List<String> tokensValides = new ArrayList<>();
	
	public String generateNewToken() {
		String token = UUID.randomUUID().toString();
		tokensValides.add(token);
		return token;
	}
	
	public boolean isTokenVAlid(String token) {
		return tokensValides.contains(token);
	}
}
