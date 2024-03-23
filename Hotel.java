import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Hotel {
    private static final AtomicInteger idCounter = new AtomicInteger(1);
    private String hotelId;
    private String name;
    private String address;
    private List<Room> rooms = new ArrayList<>();

    public Hotel(String name, String address) {
        this.hotelId = "HOTEL" + idCounter.incrementAndGet();
        this.name = name;
        this.address = address;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}
