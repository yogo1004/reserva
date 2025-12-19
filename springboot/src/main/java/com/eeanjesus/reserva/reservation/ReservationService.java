package com.eeanjesus.reserva.reservation;

import com.eeanjesus.reserva.reservation.dto.CreateReservationRequest;
import com.eeanjesus.reserva.reservation.dto.ReservationResponse;
import com.eeanjesus.reserva.room.RoomRepository;
import com.eeanjesus.reserva.user.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            RoomRepository roomRepository,
            UserRepository userRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public ReservationResponse create(CreateReservationRequest req) {
        if (!req.endTime().isAfter(req.startTime())) {
            throw new IllegalArgumentException("endTime must be after startTime");
        }

        var room = roomRepository.findById(req.roomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        var user = userRepository.findById(req.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        var reservation = new ReservationEntity();
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setStartTime(req.startTime());
        reservation.setEndTime(req.endTime());
        reservation.setTitle(req.title());
        reservation.setNotes(req.notes());

        try {
            var saved = reservationRepository.save(reservation);
            return toResponse(saved);
        } catch (DataIntegrityViolationException e) {
            // Ici PostgreSQL peut refuser à cause de la contrainte EXCLUDE (chevauchement)
            throw new IllegalStateException("Room is already booked for that time range");
        }
    }

    public List<ReservationResponse> listByRoom(Long roomId) {
        return reservationRepository.findByRoom_IdOrderByStartTimeAsc(roomId)
                .stream().map(this::toResponse).toList();
    }

    private ReservationResponse toResponse(ReservationEntity r) {
        return new ReservationResponse(
                r.getId(),
                r.getRoom().getId(),
                r.getUser().getId(),
                r.getStartTime(),
                r.getEndTime(),
                r.getTitle(),
                r.getNotes()
        );
    }
}
