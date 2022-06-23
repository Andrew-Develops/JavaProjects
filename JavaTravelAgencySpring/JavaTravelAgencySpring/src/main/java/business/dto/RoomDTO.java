package business.dto;

import javax.validation.constraints.NotNull;

public class RoomDTO {
    @NotNull
    private String type;
    @NotNull
    private int numberOfRooms;
    @NotNull
    private boolean extraBed;
    @NotNull
    private int roomsAvailable;
    @NotNull
    private double price;

    public RoomDTO(String type, int numberOfRooms, boolean extraBed, int roomsAvailable, double price) {
        this.type = type;
        this.numberOfRooms = numberOfRooms;
        this.extraBed = extraBed;
        this.roomsAvailable = roomsAvailable;
        this.price = price;
    }

    public RoomDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public boolean isExtraBed() {
        return extraBed;
    }

    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }

    public int getRoomsAvailable() {
        return roomsAvailable;
    }

    public void setRoomsAvailable(int roomsAvailable) {
        this.roomsAvailable = roomsAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "type='" + type + '\'' +
                ", numberOfRooms=" + numberOfRooms +
                ", extraBed=" + extraBed +
                ", roomsAvailable=" + roomsAvailable +
                ", price=" + price +
                '}';
    }
}
