package com.eeanjesus.reserva.user;

import com.eeanjesus.reserva.user.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UserResponse getById(Long id) {
        var u = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        return toResponse(u);
    }

    public UserResponse getByUsername(String username) {
        var u = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return toResponse(u);
    }

    public UserResponse getByUsernameAndPassword(String username, String password) {
        var u = repo.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new RuntimeException("Indvalid credentials"));
        return toResponse(u);
    }

    private UserResponse toResponse(UserEntity u) {
        return new UserResponse(u.getId(), u.getUsername(), u.getEmail(), u.getRole());
    }
}
