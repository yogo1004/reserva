package com.eeanjesus.reserva.user;

import com.eeanjesus.reserva.auth.dto.LoginResponse;
import com.eeanjesus.reserva.user.dto.UserResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // GET /api/users/1
    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET /api/users?username=admin
    @GetMapping
    public UserResponse getByUsername(@RequestParam(required = false) String username) {
        if (username == null || username.isBlank()) {
            throw new RuntimeException("Missing query param: username");
        }
        return service.getByUsername(username);
    }
    @GetMapping("/login")
    public UserResponse getByUsernameAndPassword(@RequestParam(required = false) String username, @RequestParam(required = false) String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new RuntimeException("Missing query param: username or password");
        }
        return service.getByUsernameAndPassword(username, password);
    }
}
