package com.eeanjesus.reserva.auth;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStore {

    // token -> userId
    private final Map<String, Long> tokens = new ConcurrentHashMap<>();

    public String issueToken(Long userId) {
        String token = UUID.randomUUID().toString();
        tokens.put(token, userId);
        return token;
    }

    public Long getUserId(String token) {
        return tokens.get(token);
    }

    public void revoke(String token) {
        tokens.remove(token);
    }
}
