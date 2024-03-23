import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Customer {
    private static final AtomicInteger idCounter = new AtomicInteger(1);
    private String customerId;
    private String name;
    private String email;
    private List<Booking> bookings;

    public Customer( String name, String email) {
        this.customerId = "CUSTOMER" + idCounter.incrementAndGet();
        this.name = name;
        this.email = email;
        this.bookings = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void cancelBooking(Booking booking) {
        booking.cancelBooking();
    }

    
}
