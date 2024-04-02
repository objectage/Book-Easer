package com.Manager.Hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;


import com.Manager.Hotel.entity.Hotel;
import com.Manager.Hotel.service.HotelService;

@Controller
public class HotelController {
    
        @Autowired
        private HotelService hotelService;
    
        @RequestMapping("/hotel")
        public String hotel(Model model) {
            model.addAttribute("hotel", hotelService.getAllHotels());
            return "Hotel/hotel";
        }

        @RequestMapping(value="/hotel/name", method = RequestMethod.GET)
        public String showHotelByNameForm(Model model) {
            model.addAttribute("hotel", new Hotel());
            return "Hotel/hotel_by_name";
        }

        @RequestMapping(value="/hotel/name", method = RequestMethod.POST)
        public String getHotelByName(Model model, @ModelAttribute("hotel") Hotel hotel) {
            model.addAttribute("hotel", hotelService.getHotelsByName(hotel.getName()));
            return "Hotel/hotel";
        }


        @RequestMapping(value="/hotel/city", method = RequestMethod.GET)
        public String showHotelByCityForm(Model model) {
            model.addAttribute("hotel", new Hotel());
            return "Hotel/hotel_by_city";
        }

        @RequestMapping(value="/hotel/city", method = RequestMethod.POST)
        public String getHotelByCity(Model model, @ModelAttribute("hotel") Hotel hotel) {
            model.addAttribute("hotel", hotelService.getHotelsByCity(hotel.getCity()));
            return "Hotel/hotel";
        }
    
        @RequestMapping(value="/hotel/new", method = RequestMethod.GET)
        public String showNewHotelForm(Model model) {
            model.addAttribute("hotel", new Hotel());
            return "Hotel/new_hotel";
        }
    
        @RequestMapping(value="/hotel/new", method = RequestMethod.POST)
        public String newHotel(Model model, @ModelAttribute("hotel") Hotel hotel) {
            hotelService.saveHotel(hotel);
            return "redirect:/hotel";
        }
    
        @RequestMapping(value="/hotel/edit", method = RequestMethod.GET)
        public String showEditHotelForm(Model model) {
            model.addAttribute("hotel", new Hotel());
            return "Hotel/edit_hotel";
        }
    
        @RequestMapping(value="/hotel/edit", method = RequestMethod.POST)
        public String editHotel(Model model, @ModelAttribute("hotel") Hotel hotel) {
            hotelService.updateHotel(hotel);
            return "redirect:/hotel";
        }
    
        @RequestMapping(value="/hotel/delete", method = RequestMethod.GET)
        public String showDeleteHotelForm(Model model) {
            model.addAttribute("hotel", new Hotel());
            return "Hotel/delete_hotel";
        }
    
        @RequestMapping(value="/hotel/delete", method = RequestMethod.POST)
        public String deleteHotel(Model model, @ModelAttribute("hotel") Hotel hotel) {
            hotelService.deleteHotel(hotel.getId());
            return "redirect:/hotel";
        }
}
