package com.eeanjesus.reserva.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    List<RoomEntity> findByIsActiveTrue();


    List<RoomEntity> findAll();
}
