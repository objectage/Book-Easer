package com.Manager.Hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;


import com.Manager.Hotel.entity.Room;
import com.Manager.Hotel.service.RoomService;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/room")
    public String room(Model model) {
        model.addAttribute("room", roomService.getAllRooms());
        return "room";
    }

    @RequestMapping(value="/room/new", method = RequestMethod.GET)
    public String showNewRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "new_room";
    }

    @RequestMapping(value="/room/new", method = RequestMethod.POST)
    public String newRoom(Model model, @ModelAttribute("room") Room room) {
        roomService.saveRoom(room);
        return "redirect:/room";
    }

    @RequestMapping(value="/room/edit", method = RequestMethod.GET)
    public String showEditRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "edit_room";
    }

    @RequestMapping(value="/room/edit", method = RequestMethod.POST)
    public String editRoom(Model model, @ModelAttribute("room") Room room) {
        roomService.updateRoom(room);
        return "redirect:/room";
    }

    @RequestMapping(value="/room/delete", method = RequestMethod.GET)
    public String showDeleteRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "delete_room";
    }

    @RequestMapping(value="/room/delete", method = RequestMethod.POST)
    public String deleteRoom(Model model, @ModelAttribute("room") Room room) {
        roomService.deleteRoom(room.getId());
        return "redirect:/room";
    }

    @RequestMapping(value="/room/availability", method = RequestMethod.GET)
    public String showAvailabiltyRoom(Model model) {
        model.addAttribute("room", new Room());
        return "availability";
    }

    @RequestMapping(value="/room/availability")
    public String availableRoom(Model model) {
        model.addAttribute("room", roomService.getAvailableRooms("available"));
        return "room";
    }   

    @RequestMapping(value="/room/unavailable", method = RequestMethod.GET) 
    public String showUnavailableRoom(Model model) {
        model.addAttribute("room", new Room());
        return "unavailable_room";
    }
    
    @RequestMapping(value="/room/unavailable")
    public String unavailableRoom(Model model) {
        model.addAttribute("room", roomService.getAvailableRooms("unavailable"));
        return "room";
    }

    
    @RequestMapping(value="/room/type", method = RequestMethod.GET) 
    public String showRoomType(Model model) {
        model.addAttribute("room", new Room());
        return "room_type";
    }

    @RequestMapping(value="/room/type", method = RequestMethod.POST)
    public String roomType(Model model, @ModelAttribute("room") Room room) {
        model.addAttribute("room", roomService.getRoomsByType(room.getType()));
        return "room";
    }
    
}