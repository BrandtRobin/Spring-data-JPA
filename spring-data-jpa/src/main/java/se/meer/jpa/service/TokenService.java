package se.meer.jpa.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import se.meer.jpa.model.Token;
import se.meer.jpa.repository.TokenRepository;

public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	public Token createToken(String username) {
        String tokenString = createTokenString();
        Token token = new Token(username, tokenString);
		return tokenRepository.save(token);
	}
	
	public void deleteTokenByUsername(String username){
		tokenRepository.delete(tokenRepository.findTokenByUsername(username));
	}
	
	public Token findTokenByUsername(String username) {
		return tokenRepository.findTokenByUsername(username);
	}
	
    public String createTokenString() {
	    Random random = new SecureRandom();
	    String token = new BigInteger(130, random).toString(32);
	    return token;
    }
}
