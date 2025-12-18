package com.eeanjesus.reserva.user;

import com.eeanjesus.reserva.auth.TokenStore;
import com.eeanjesus.reserva.user.dto.MeResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MeController {

    private final TokenStore tokenStore;
    private final UserRepository userRepository;

    public MeController(TokenStore tokenStore, UserRepository userRepository) {
        this.tokenStore = tokenStore;
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public MeResponse me(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing Authorization header");
        }

        String token = authHeader.substring("Bearer ".length());
        Long userId = tokenStore.getUserId(token);
        if (userId == null) throw new RuntimeException("Invalid token");

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new MeResponse(user.getId(), user.getUsername(), user.getRole());
    }
}
