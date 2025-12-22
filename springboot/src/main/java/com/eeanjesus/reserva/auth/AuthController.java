package com.eeanjesus.reserva.auth;

import com.eeanjesus.reserva.auth.jwt.JwtService;
import com.eeanjesus.reserva.user.UserRepository;
import org.springframework.web.bind.annotation.*;
import com.eeanjesus.reserva.auth.dto.LoginRequest;
import com.eeanjesus.reserva.auth.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        var user = userRepository.findByUsername(req.username())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // TEMP: password en clair (plus tard BCrypt)
        if (!user.getPassword().equals(req.password())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(
                user.getUsername(),
                user.getId(),
                user.getRole()
        );

        return new LoginResponse(token);
    }
}
