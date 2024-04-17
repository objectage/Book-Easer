package com.Manager.Hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import com.Manager.Hotel.entity.Room;
import com.Manager.Hotel.service.RoomService;


@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/room")
    public String room(Model model) {
        model.addAttribute("room", roomService.getAllRooms());
        return "Room/room";
    }

    @RequestMapping(value="/room/new", method = RequestMethod.GET)
    public String showNewRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "Room/new_room";
    }

    @RequestMapping(value="/room/new", method = RequestMethod.POST)
    public String newRoom(Model model, @ModelAttribute("room") Room room, @RequestParam("hotelId") Long hotelId) {
        roomService.saveRoom(room, hotelId);
        return "redirect:/room";
    }

    @RequestMapping(value="/room/edit", method = RequestMethod.GET)
    public String showEditRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "Room/edit_room";
    }

    @RequestMapping(value="/room/edit", method = RequestMethod.POST)
    public String editRoom(Model model, @ModelAttribute("room") Room room) {
        roomService.updateRoom(room);
        return "redirect:/room";
    }

    @RequestMapping(value="/room/delete", method = RequestMethod.GET)
    public String showDeleteRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "Room/delete_room";
    }

    @RequestMapping(value="/room/delete", method = RequestMethod.POST)
    public String deleteRoom(Model model, @ModelAttribute("room") Room room) {
        roomService.deleteRoom(room.getId());
        return "redirect:/room";
    }

    @RequestMapping(value="/room/available", method = RequestMethod.GET)
    public String availableRoom(Model model) {
        List<Room> unavailableRooms = roomService.getAvailableRooms(true);
        model.addAttribute("rooms", unavailableRooms);
        return "Room/unavailable";
    }

    
    @RequestMapping(value="/room/unavailable", method = RequestMethod.GET)
    public String unavailableRoom(Model model) {
        List<Room> unavailableRooms = roomService.getAvailableRooms(false);
        model.addAttribute("rooms", unavailableRooms);
        return "Room/unavailable";
    }
    
    @RequestMapping(value="/room/type", method = RequestMethod.GET) 
    public String showRoomType(Model model) {
        model.addAttribute("room", new Room());
        return "Room/room_type";
    }

    @RequestMapping(value="/room/type", method = RequestMethod.POST)
    public String roomType(Model model, @ModelAttribute("room") Room room) {
        model.addAttribute("room", roomService.getRoomsByType(room.getType()));
        return "Room/room";
    }

    @PostMapping("/room/updateAvailability")
    public String updateAvailability(@RequestParam("id") Long roomId) {
        roomService.updateRoomAvailability(roomId);
        return "redirect:/room";
    }

    
}