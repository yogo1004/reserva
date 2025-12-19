package com.eeanjesus.reserva.room;

import com.eeanjesus.reserva.room.dto.RoomResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomResponse> getAllActiveRooms() {

        return roomRepository.findByIsActiveTrue()
                .stream()
                .map(r -> new RoomResponse(r.getId(), r.getName(), r.getLocation(), r.getCapacity(), r.isActive()))
                .toList();
    }

    public List<RoomResponse> getAllRooms(){
        return roomRepository.findAll()
                .stream()
                .map(r -> new RoomResponse(r.getId(), r.getName(), r.getLocation(), r.getCapacity(), r.isActive()))
                .toList();
    }
}
