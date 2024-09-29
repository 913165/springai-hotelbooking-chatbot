package com.example.application.model;

import java.util.ArrayList;
import java.util.List;

public class HotelBookingData {

    private List<Customer> customers = new ArrayList<>();
    private List<HotelBooking> hotelBookings = new ArrayList<>();

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<HotelBooking> getHotelBookings() {
        return hotelBookings;
    }

    public void setHotelBookings(List<HotelBooking> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }
}
