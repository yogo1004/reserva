package com.eeanjesus.reserva.User;

import com.eeanjesus.reserva.User.UserResponse;
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

  // @GetMapping("/{username}")
  // public UserResponse getByUsername(@PathVariable String username) {
  //     return service.getByUsername(username);
  // }

    // GET /api/users?username=admin
    @GetMapping
    public UserResponse getByUsername(@RequestParam(required = false) String username) {
        if (username == null || username.isBlank()) {
            throw new RuntimeException("Missing query param: username");
        }
        return service.getByUsername(username);
    }
}
