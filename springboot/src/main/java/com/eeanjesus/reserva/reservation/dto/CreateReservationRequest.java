package com.eeanjesus.reserva.reservation.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateReservationRequest(
        @NotNull Long roomId,
        @NotNull Long userId,   // temporaire (plus tard: depuis le token)
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime,
        String title,
        String notes
) {}
