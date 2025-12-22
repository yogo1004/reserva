package com.eeanjesus.reserva.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findByRoom_IdOrderByStartTimeAsc(Long roomId);

    List<ReservationEntity> findByUser_IdOrderByStartTimeAsc(Long userId);

    List<ReservationEntity> findByRoom_IdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long roomId, LocalDateTime to, LocalDateTime from
    );
}
