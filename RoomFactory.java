public class RoomFactory {
    public static Room createRoom(String roomId, String type, double price) {
        return new Room(type, price);
    }
}
