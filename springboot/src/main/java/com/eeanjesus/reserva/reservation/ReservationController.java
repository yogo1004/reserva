package com.eeanjesus.reserva.reservation;

import com.eeanjesus.reserva.reservation.dto.CreateReservationRequest;
import com.eeanjesus.reserva.reservation.dto.ReservationResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // POST /api/reservations
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateReservationRequest req) {
        try {
            return ResponseEntity.ok(reservationService.create(req));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            // conflit de réservation
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    // GET /api/reservations/room/1
    @GetMapping("/room/{roomId}")
    public List<ReservationResponse> listByRoom(@PathVariable Long roomId) {
        return reservationService.listByRoom(roomId);
    }
}
