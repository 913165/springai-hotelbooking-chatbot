package com.example.application.client;

import com.example.application.services.HotelBookingDetails;
import com.example.application.services.HotelBookingService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import java.util.List;

@BrowserCallable
@AnonymousAllowed
public class BookingService {
    private final HotelBookingService hotelBookingService;

    public BookingService(HotelBookingService hotelBookingService) {
        this.hotelBookingService = hotelBookingService;
    }

    public List<HotelBookingDetails> getBookings() {
        return hotelBookingService.getBookings();
    }
}
