import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Booking {
    private static final AtomicInteger idCounter = new AtomicInteger(1);
    private String bookingId;
    private String customerId;
    private String roomId;
    private Date startDate;
    private int noOfDays;

    public Booking(String customerId, String roomId, Date startDate, int noOfDays) {
        this.bookingId = "BOOKING" + idCounter.incrementAndGet();
        this.customerId = customerId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.noOfDays = noOfDays;
    }
    
    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getRoomId() {
        return roomId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void cancelBooking() {
        this.noOfDays=-1;
        System.out.println("Booking canceled successfully.");
    }

}
