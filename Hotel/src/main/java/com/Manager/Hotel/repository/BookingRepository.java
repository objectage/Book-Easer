package com.Manager.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Manager.Hotel.entity.Booking;

import java.util.List;
import java.util.Date;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerId(Long customerId);

    List<Booking> findByRoomId(Long roomId);

    List<Booking> findByStartDate(Date startDate);



}
