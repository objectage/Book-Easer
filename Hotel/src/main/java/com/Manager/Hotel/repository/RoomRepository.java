package com.Manager.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Manager.Hotel.entity.Room;

public interface RoomRepository extends JpaRepository<Room, String> {

}
