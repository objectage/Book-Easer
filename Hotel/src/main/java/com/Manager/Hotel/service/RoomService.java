package com.Manager.Hotel.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.Manager.Hotel.repository.RoomRepository;
import com.Manager.Hotel.entity.Room;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getRoom(int id) {
        return roomRepository.findById(id).orElse(null);
    }

    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getAvailableRooms(String status) {
        return roomRepository.findByAvailability(status);
    }

    public List<Room> getRoomsByType(String type) {
        return roomRepository.findByType(type);
    }

    public List<Room> getRoomsByPrice(double price) {
        return roomRepository.findByPrice(price);
    }

    public List<Room> getRoomsByTypeAndAvailability(String type, String availability) {
        return roomRepository.findByTypeAndAvailability(type, availability);
    }

    public Room updateRoomAvailability(int id, Boolean availability) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            room.setAvailability(availability);
            return roomRepository.save(room);
        }
        return null;
    }

}
