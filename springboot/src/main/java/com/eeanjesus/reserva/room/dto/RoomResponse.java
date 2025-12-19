package com.eeanjesus.reserva.room.dto;

public record RoomResponse(
        Long id,
        String name,
        String location,
        int capacity,
        boolean is_active
) {}
