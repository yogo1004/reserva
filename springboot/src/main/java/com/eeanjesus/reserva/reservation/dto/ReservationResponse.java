package com.eeanjesus.reserva.reservation.dto;

import java.time.LocalDateTime;

public record ReservationResponse(
        Long id,
        Long roomId,
        Long userId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String title,
        String notes
) {}
