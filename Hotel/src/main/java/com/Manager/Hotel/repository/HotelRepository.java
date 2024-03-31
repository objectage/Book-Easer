package com.Manager.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Manager.Hotel.entity.Hotel;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByName(String name);

    List<Hotel> findByCity(String city);

}
