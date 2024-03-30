package com.Manager.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Manager.Hotel.entity.Booking;

import java.util.List;
import java.util.Date;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByCustomerID(int customerID);

    List<Booking> findByRoomID(int roomID);

    List<Booking> findByStartDate(Date startDate);



}
