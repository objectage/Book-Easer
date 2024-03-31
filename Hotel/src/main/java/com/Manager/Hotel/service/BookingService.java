package com.Manager.Hotel.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.Manager.Hotel.repository.BookingRepository;
import com.Manager.Hotel.entity.Booking;
// import com.Manager.Hotel.entity.Customer;    
// import com.Manager.Hotel.entity.Room;
// import com.Manager.Hotel.service.RoomService;
// import com.Manager.Hotel.service.CustomerService;
import java.util.Date;

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

    public Booking getBooking(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByCustomerID(Long customerID) {
        return bookingRepository.findByCustomerId(customerID);
    }

    public List<Booking> getBookingsByRoomID(Long roomID) {
        return bookingRepository.findByRoomId(roomID);
    }

    public List<Booking> getBookingsByStartDate(Date startDate) {
        return bookingRepository.findByStartDate(startDate);
    }

    public Booking updateBookingPaymentStatus(Long id, Boolean paymentStatus) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setPaymentStatus(paymentStatus);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public Booking updateBookingCheckInStatus(Long id, Boolean checkInStatus) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setCheckInStatus(checkInStatus);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public Booking updateBookingCheckOutStatus(Long id, Boolean checkOutStatus) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setCheckOutStatus(checkOutStatus);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public Booking updateBookingTotalPrice(Long id, double totalPrice) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setTotalPrice(totalPrice);
            return bookingRepository.save(booking);
        }
        return null;
    }

    @Transactional
    public Booking calculateTotalPrice(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null && booking.getRoom() != null) {
            double totalPrice = booking.getNoOfDays() * booking.getRoom().getPrice();
            booking.setTotalPrice(totalPrice);
            bookingRepository.save(booking);
        }
        return booking;
    }

}
