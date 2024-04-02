package com.Manager.Hotel.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.Manager.Hotel.repository.HotelRepository;
import com.Manager.Hotel.entity.Hotel;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel saveHotel(Hotel hotel) {
        Hotel hotel1=new Hotel();
        hotel1.setId(hotel.getId());
        hotel1.setCity(hotel.getCity());
        hotel1.setName(hotel.getName());
        return hotelRepository.save(hotel1);
    }

    public Hotel updateHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel getHotel(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public List<Hotel> getHotelsByName(String name) {
        return hotelRepository.findByName(name);
    }

    public List<Hotel> getHotelsByCity(String city) {
        return hotelRepository.findByCity(city);
    }



}
