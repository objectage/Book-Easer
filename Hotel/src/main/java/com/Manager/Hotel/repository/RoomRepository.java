package com.Manager.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Manager.Hotel.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByAvailability(String availability);

    List<Room> findByType(String type);

    List<Room> findByPrice(double price);

    List<Room> findByTypeAndAvailability(String type, String availability);

}
