package com.Manager.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Manager.Hotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
