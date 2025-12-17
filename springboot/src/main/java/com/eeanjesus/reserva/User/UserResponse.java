package com.eeanjesus.reserva.User;

public record UserResponse(
        Long id,
        String username,
        String email,
        String role
) {}
