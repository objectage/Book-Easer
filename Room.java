import java.util.concurrent.atomic.AtomicInteger;


public class Room {
    private static final AtomicInteger idCounter = new AtomicInteger(1);
    private String roomId;
    private String type;
    private boolean availability;
    private String bookingID;
    private double price;

    public Room(String type, double price) {
        this.roomId = "ROOM" + Room.idCounter.incrementAndGet();
        this.type = type;
        this.availability = true; 
        this.price = price;
        this.bookingID = null;
    }

    public String getRoomId() {
        return roomId;
    }
    public String getType() {
        return type;
    }
    public boolean isAvailable() {
        return availability;
    }
    public double getPrice() {
        return price;
    }
    
    public void bookRoom() {
        this.availability = false;
    }

    public void releaseRoom() {
        this.availability = true;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }
    
}
