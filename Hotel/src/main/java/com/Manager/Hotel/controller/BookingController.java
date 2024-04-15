package com.Manager.Hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Manager.Hotel.entity.Booking;
import com.Manager.Hotel.service.BookingService;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/booking")
    public String allBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "Booking/booking";
    }

    @PostMapping("/booking/checkIn/{id}")
    public String checkInBooking(@PathVariable("id") Long bookingId) {
        bookingService.updateBookingCheckInStatus(bookingId,true);
        return "redirect:/booking";
    }

    @PostMapping("/booking/checkOut/{id}")
    public String checkOutBooking(@PathVariable("id") Long bookingId) {
        bookingService.updateBookingCheckOutStatus(bookingId,true);
        return "redirect:/booking";
    }

    @PostMapping("/booking/pay/{id}")
    public String payBooking(@PathVariable("id") Long bookingId) {
        bookingService.updateBookingPaymentStatus(bookingId,true);
        return "redirect:/booking";   
    }
}
