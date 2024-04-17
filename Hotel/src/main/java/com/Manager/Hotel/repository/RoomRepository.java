package com.Manager.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Manager.Hotel.entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r JOIN FETCH r.hotel")
    List<Room> findAll();

    List<Room> findByAvailability(Boolean availability);

    List<Room> findByType(String type);

    List<Room> findByPrice(double price);

    List<Room> findByTypeAndAvailability(String type, Boolean availability);

    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId")
    List<Room> findByHotelId(@Param("hotelId") Long hotelId);

    @Query("SELECT r FROM Room r WHERE r.availability = :availability AND r.hotel.id = :hotelId")
    List<Room> findByAvailabilityAndHotelId(@Param("availability") Boolean availability, @Param("hotelId") Long hotelId);
}

