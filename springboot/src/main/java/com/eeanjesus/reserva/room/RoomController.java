package com.eeanjesus.reserva.room;

import com.eeanjesus.reserva.room.dto.RoomResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // GET /api/rooms
    @GetMapping("/true")
    public List<RoomResponse> getRoomsIsActiveTrue() {
        return roomService.getAllActiveRooms();
    }

    @GetMapping
    public List<RoomResponse> getRooms() {
        return roomService.getAllRooms();
    }
}
