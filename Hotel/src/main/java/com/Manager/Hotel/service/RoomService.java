package com.Manager.Hotel.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.Manager.Hotel.repository.RoomRepository;
import com.Manager.Hotel.repository.HotelRepository;
import com.Manager.Hotel.entity.Hotel;
import com.Manager.Hotel.entity.Room;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public Room saveRoom(Room room, Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found with id " + hotelId));
        
        room.setHotel(hotel);
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getRoom(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getAvailableRooms(Boolean status) {
        return roomRepository.findByAvailability(status);
    }

    public List<Room> getRoomsByType(String type) {
        return roomRepository.findByType(type);
    }

    public List<Room> getRoomsByPrice(double price) {
        return roomRepository.findByPrice(price);
    }

    public List<Room> getRoomsByTypeAndAvailability(String type, Boolean availability) {
        return roomRepository.findByTypeAndAvailability(type, availability);
    }

    public Room updateRoomAvailability(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            boolean newAvailability = !room.getAvailability();
            room.setAvailability(newAvailability);
            System.out.println("Updating room availability to: " + newAvailability);
            return roomRepository.save(room);
        }
        return null;
    }
    

}
