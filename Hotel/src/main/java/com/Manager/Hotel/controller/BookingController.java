package com.Manager.Hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;


import com.Manager.Hotel.entity.Booking;
import com.Manager.Hotel.service.BookingService;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/booking")
    public String booking(Model model) {
        model.addAttribute("booking", bookingService.getAllBookings());
        return "booking";
    }

    @RequestMapping(value="/booking/customerID", method = RequestMethod.GET)
    public String showBookingByCustomerIDForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking_by_customerID";
    }

    @RequestMapping(value="/booking/customerID", method = RequestMethod.POST)
    public String getBookingByCustomerID(Model model, @ModelAttribute("booking") Booking booking) {
        model.addAttribute("booking", bookingService.getBookingsByCustomerID(booking.getCustomerID()));
        return "booking";
    }

    @RequestMapping(value="/booking/roomID", method = RequestMethod.GET)
    public String showBookingByRoomIDForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking_by_roomID";
    }

    @RequestMapping(value="/booking/roomID", method = RequestMethod.POST)
    public String getBookingByRoomID(Model model, @ModelAttribute("booking") Booking booking) {
        model.addAttribute("booking", bookingService.getBookingsByRoomID(booking.getRoomID()));
        return "booking";
    }

    @RequestMapping(value="/booking/startDate", method = RequestMethod.GET)
    public String showBookingByStartDateForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking_by_startDate";
    }

    @RequestMapping(value="/booking/startDate", method = RequestMethod.POST)
    public String getBookingByStartDate(Model model, @ModelAttribute("booking") Booking booking) {
        model.addAttribute("booking", bookingService.getBookingsByStartDate(booking.getStartDate()));
        return "booking";
    }

    @RequestMapping(value="/booking/new", method = RequestMethod.GET)
    public String showNewBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "new_booking";
    }

    @RequestMapping(value="/booking/new", method = RequestMethod.POST)
    public String newBooking(Model model, @ModelAttribute("booking") Booking booking) {
        bookingService.saveBooking(booking);
        return "redirect:/booking";
    }

    @RequestMapping(value="/booking/edit", method = RequestMethod.GET)
    public String showEditBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "edit_booking";
    }

    @RequestMapping(value="/booking/edit", method = RequestMethod.POST)
    public String editBooking(Model model, @ModelAttribute("booking") Booking booking) {
        bookingService.updateBooking(booking);
        return "redirect:/booking";
    }

    @RequestMapping(value="/booking/delete", method = RequestMethod.GET)
    public String showDeleteBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "delete_booking";
    }

    @RequestMapping(value="/booking/delete", method = RequestMethod.POST)
    public String deleteBooking(Model model, @ModelAttribute("booking") Booking booking) {
        bookingService.deleteBooking(booking.getId());
        return "redirect:/booking";
    }

    @RequestMapping(value="/booking/paymentStatus", method = RequestMethod.GET)
    public String showUpdateBookingPaymentStatusForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "update_booking_paymentStatus";
    }

    @RequestMapping(value="/booking/paymentStatus", method = RequestMethod.POST)
    public String updateBookingPaymentStatus(Model model, @ModelAttribute("booking") Booking booking) {
        bookingService.updateBookingPaymentStatus(booking.getId(), booking.getPaymentStatus());
        return "redirect:/booking";
    }   

    @RequestMapping(value="/booking/checkInStatus", method = RequestMethod.GET)
    public String showUpdateBookingCheckInStatusForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "update_booking_checkInStatus";
    }

    @RequestMapping(value="/booking/checkInStatus", method = RequestMethod.POST)
    public String updateBookingCheckInStatus(Model model, @ModelAttribute("booking") Booking booking) {
        bookingService.updateBookingCheckInStatus(booking.getId(), booking.getCheckInStatus());
        return "redirect:/booking";
    }

    @RequestMapping(value="/booking/checkOutStatus", method = RequestMethod.GET)
    public String showUpdateBookingCheckOutStatusForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "update_booking_checkOutStatus";
    }

    @RequestMapping(value="/booking/checkOutStatus", method = RequestMethod.POST)
    public String updateBookingCheckOutStatus(Model model, @ModelAttribute("booking") Booking booking) {
        bookingService.updateBookingCheckOutStatus(booking.getId(), booking.getCheckOutStatus());
        return "redirect:/booking";
    }
}
