// Got this off GPT, so it doesnt fit code yet, will need to be modified
// there os also a bookingmanager implemented so go through that to be able to retrieve objects using IDs
// This is a facade class that will be used to book a room, cancel a booking and release a room, we can add more methods as needed

import java.sql.Date;

public class BookingFacade {
    private Hotel hotel;
    private BookingManager bookingManager;

    public BookingFacade(Hotel hotel, BookingManager bookingManager) {
        this.hotel = hotel;
        this.bookingManager = bookingManager;
    }

    public void bookRoom(String customerId, String roomId, Date startDate, int noOfDays) {
        Room room = hotel.getRooms().stream().filter(r -> r.getRoomId().equals(roomId) && r.isAvailable()).findFirst().orElse(null);
        if (room != null) {
            room.bookRoom();
            Booking booking = new Booking(customerId, roomId, startDate, noOfDays);
            bookingManager.addBookingInManager(booking);
            System.out.println("Room booked successfully, Booking ID: " + booking.getBookingId());
        } else {
            System.out.println("Room is not available.");
        }
    }

    public void cancelBooking(String bookingId) {
        bookingManager.cancelBookingInManager(bookingId);

    }

    public void releaseRoom(String roomId) {
        Room room = hotel.getRooms().stream().filter(r -> r.getRoomId().equals(roomId)).findFirst().orElse(null);
        if (room != null) {
            room.releaseRoom();
            System.out.println("Room released successfully.");
        } else {
            System.out.println("Room not found.");
        }
    }

}
