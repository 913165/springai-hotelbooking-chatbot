package com.example.application.model;

import java.time.LocalDate;

public class HotelBooking {

    private String bookingNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Customer customer;
    private String hotelName;
    private RoomType roomType; // e.g., Single, Double, Suite
    private int numberOfGuests;
    private HotelBookingStatus bookingStatus;

    public HotelBooking(String bookingNumber, LocalDate checkInDate, LocalDate checkOutDate, Customer customer, String hotelName, RoomType roomType, int numberOfGuests, HotelBookingStatus bookingStatus) {
        this.bookingNumber = bookingNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.customer = customer;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.numberOfGuests = numberOfGuests;
        this.bookingStatus = bookingStatus;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public HotelBookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(HotelBookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
