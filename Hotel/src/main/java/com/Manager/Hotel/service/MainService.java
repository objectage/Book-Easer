package com.Manager.Hotel.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.Manager.Hotel.repository.BookingRepository;
import com.Manager.Hotel.repository.RoomRepository;
import com.Manager.Hotel.repository.CustomerRepository;
import com.Manager.Hotel.repository.HotelRepository;
import com.Manager.Hotel.entity.Booking;
import com.Manager.Hotel.entity.Customer;
import com.Manager.Hotel.entity.Hotel;
import com.Manager.Hotel.entity.Room;

@Service
public class MainService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HotelRepository hotelRepository;
  
    public Customer createAccount(Customer customer) {  
        return customerRepository.save(customer);
    }

    public Customer login(Customer customer) {
        return customerRepository.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
    }

    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    public List<Room> getRoomsByHotelID(Long hotelID) {
        return roomRepository.findByHotelId(hotelID);
    }

    public List<Room> getRoomsByAvailability(Boolean availability, Long hotelId) {
        return roomRepository.findByAvailabilityAndHotelId(availability, hotelId);
    }

    public Booking makeBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking getBooking(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Room updateRoomAvailability(Long roomID, Boolean availability) {
        Room room = roomRepository.findById(roomID).orElse(null);
        if (room != null) {
            room.setAvailability(availability);
            return roomRepository.save(room);
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

    public Booking updateBookingPaymentStatus(Long id, Boolean paymentStatus) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setPaymentStatus(paymentStatus);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public List<Booking> getBookingsByCustomerID(Long customerID) {
        return bookingRepository.findByCustomerId(customerID);
    }

    public Customer setSubcriptionCustomer(Long customerID, Boolean sub) {
        Customer customer = customerRepository.findById(customerID).orElse(null);
        if (customer != null) {
            customer.setSub(sub);
            System.out.println(customer.isSubscribed());
            return customerRepository.save(customer);
        }
        return customer;
    }

}
