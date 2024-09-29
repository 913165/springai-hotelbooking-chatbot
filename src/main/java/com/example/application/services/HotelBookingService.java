package com.example.application.services;

import com.example.application.model.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@BrowserCallable
@AnonymousAllowed
@Service
public class HotelBookingService {


    private final HotelBookingData hotelBookingData;


    public HotelBookingService() {
        hotelBookingData = new HotelBookingData();
        initData();
    }

    private void initData() {
        List<String> firstNames = List.of("John", "Jane", "Emma", "Sophia", "Olivia");
        List<String> lastNames = List.of("Doe", "Smith", "Brown", "Wilson", "Moore");
        List<String> hotelNames = List.of("Grand Hotel", "Central Hotel", "Island Hotel", "Coast Hotel", "Beach Hotel");

        var customers = new ArrayList<Customer>();
        var bookings = new ArrayList<HotelBooking>();

        for (int i = 0; i < 5; i++) {
            String firstName = firstNames.get(i);
            String lastName = lastNames.get(i);
            String hotelName = hotelNames.get(i);

            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);

            RoomType roomType = RoomType.values()[i % RoomType.values().length];

            HotelBooking booking = new HotelBooking("10" + (i + 1), LocalDate.now().plusDays(2 * i), LocalDate.now().plusDays(2 * i + 2), customer, hotelName, roomType, 2, HotelBookingStatus.CONFIRMED);
            customer.getBookings().add(booking);

            customers.add(customer);
            bookings.add(booking);
        }
        hotelBookingData.setCustomers(customers);
        hotelBookingData.setHotelBookings(bookings);
    }

    public List<HotelBookingDetails> getBookings() {
        return hotelBookingData.getHotelBookings().stream().map(this::toHotelBookingDetails).toList();
    }

    private HotelBooking findBooking(String bookingNumber, String firstName, String lastName) {
        return hotelBookingData.getHotelBookings().stream()
                .filter(b -> b.getBookingNumber().equalsIgnoreCase(bookingNumber))
                .filter(b -> b.getCustomer().getFirstName().equalsIgnoreCase(firstName))
                .filter(b -> b.getCustomer().getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
    }

    public void cancelBooking(String bookingNumber, String firstName, String lastName) {
        var booking = findBooking(bookingNumber, firstName, lastName);
        booking.setBookingStatus(HotelBookingStatus.CANCELLED.CANCELLED);
    }

    public void roomTypeChangeRequest(String bookingNumber, String firstName, String lastName, String roomType) {
        RoomType updatedRoomType = RoomType.valueOf(roomType);
        var booking = findBooking(bookingNumber, firstName, lastName);
        booking.setRoomType(updatedRoomType);

    }

    public HotelBookingDetails toHotelBookingDetails(HotelBooking hotelBooking) {
        return new HotelBookingDetails(
                hotelBooking.getBookingNumber(),
                hotelBooking.getCustomer().getFirstName(),
                hotelBooking.getCustomer().getLastName(),
                hotelBooking.getCheckInDate(),
                hotelBooking.getCheckOutDate(),
                hotelBooking.getBookingStatus(),
                hotelBooking.getHotelName(),
                hotelBooking.getRoomType(),
                hotelBooking.getNumberOfGuests()
        );
    }
}
