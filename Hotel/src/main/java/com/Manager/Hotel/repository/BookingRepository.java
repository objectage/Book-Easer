package com.Manager.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Manager.Hotel.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, String> {

}
