package com.eeanjesus.reserva.auth;

import com.eeanjesus.reserva.auth.dto.LoginRequest;
import com.eeanjesus.reserva.auth.dto.LoginResponse;
import com.eeanjesus.reserva.user.UserEntity;
import com.eeanjesus.reserva.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {

        // 1️⃣ Chercher l’utilisateur par username
        UserEntity user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // 2️⃣ Vérifier le mot de passe (TEMPORAIRE : en clair)
        if (!user.getPassword().equals(request.password())) {
            throw new RuntimeException("Invalid credentials");
        }

        // 3️⃣ Générer un token (TEMPORAIRE)
        String fakeToken = "TOKEN_" + user.getId() + "_" + System.currentTimeMillis();

        // 4️⃣ Retourner la réponse
        return new LoginResponse(fakeToken);
    }
}
