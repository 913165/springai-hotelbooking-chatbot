package com.example.application.config;

import com.example.application.services.HotelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;
@Configuration
public class FunctionConfig {

    @Autowired
    HotelBookingService hotelBookingService;
    @Bean
    @Description("Cancel booking")
    public Function<CancelBookingRequest, String> cancelBooking() {
        return request -> {
            hotelBookingService.cancelBooking(request.bookingNumber(), request.firstName(), request.lastName());
            return "";
        };
    }

    public record CancelBookingRequest(String bookingNumber, String firstName, String lastName) {
    }

    @Bean
    @Description("Room type change request")
    public Function<RoomTypeChangeRequest, String> roomTypeChangeRequest() {
        return request -> {
            hotelBookingService.roomTypeChangeRequest(request.bookingNumber(), request.firstName(), request.lastName(), request.roomType());
            return "";
        };
    }

    public record RoomTypeChangeRequest(String bookingNumber, String firstName, String lastName, String roomType) {
    }
}
