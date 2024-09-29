package com.example.application.services;

import com.example.application.model.HotelBookingStatus;
import com.example.application.model.RoomType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.LocalDate;


@JsonInclude(Include.NON_NULL)
public record HotelBookingDetails(
        String bookingNumber,
        String firstName,
        String lastName,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        HotelBookingStatus bookingStatus,
        String hotelName,
        RoomType roomType,
        int numberOfGuests) {
}
