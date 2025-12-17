package com.eeanjesus.reserva.user.dto;

public record UserResponse(
        Long id,
        String username,
        String email,
        String role
) {}

