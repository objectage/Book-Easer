package com.Manager.Hotel.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.Manager.Hotel.repository.BookingRepository;
import com.Manager.Hotel.entity.Booking;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking getBooking(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByCustomerID(int customerID) {
        return bookingRepository.findByCustomerID(customerID);
    }

    public List<Booking> getBookingsByRoomID(int roomID) {
        return bookingRepository.findByRoomID(roomID);
    }

    public List<Booking> getBookingsByStartDate(int startDate) {
        return bookingRepository.findByStartDate(startDate);
    }

    public Booking updateBookingPaymentStatus(int id, Boolean paymentStatus) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setPaymentStatus(paymentStatus);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public Booking updateBookingCheckInStatus(int id, Boolean checkInStatus) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setCheckInStatus(checkInStatus);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public Booking updateBookingCheckOutStatus(int id, Boolean checkOutStatus) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setCheckOutStatus(checkOutStatus);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public Booking updateBookingTotalPrice(int id, double totalPrice) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setTotalPrice(totalPrice);
            return bookingRepository.save(booking);
        }
        return null;
    }

    @Transactional
    public Booking calculateTotalPrice(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null && booking.getRoom() != null) {
            double totalPrice = booking.getNoOfDays() * booking.getRoom().getPrice();
            booking.setTotalPrice(totalPrice);
            bookingRepository.save(booking);
        }
        return booking;
    }

}
