
import {useEffect, useState} from "react";

import {BookingService} from 'Frontend/generated/endpoints.js';
import {GridColumn} from "@vaadin/react-components/GridColumn";
import {Grid} from "@vaadin/react-components/Grid";
import {SplitLayout} from "@vaadin/react-components/SplitLayout";
import HotelBookingDetails from "../generated/com/example/application/services/HotelBookingDetails";


export default function Index() {
    const [working, setWorking] = useState(false);
    const [bookings, setBookings] = useState<HotelBookingDetails[]>([]);

    useEffect(() => {
        // Update bookings when we have received the full response
        if (!working) {
            BookingService.getBookings().then(setBookings);
        }
    }, [working]);

    return (
        <SplitLayout className="h-full">

            <div className="flex flex-col gap-m p-m box-border" style={{width: '70%'}}>
                <h3>Hotel Bookings Database</h3>
                <Grid items={bookings} className="flex-shrink-0">
                    <GridColumn path="bookingNumber" autoWidth header="#"/>
                    <GridColumn path="firstName" autoWidth header="First Name"/>
                    <GridColumn path="lastName" autoWidth header="Last Name"/>
                    <GridColumn path="checkInDate" autoWidth header="Check-In"/>
                    <GridColumn path="checkOutDate" autoWidth header="Check-Out"/>
                    <GridColumn path="hotelName" autoWidth header="Hotel"/>
                    <GridColumn path="roomType" autoWidth header="Room Type"/>
                    <GridColumn path="numberOfGuests" autoWidth header="Guests"/>
                    <GridColumn path="bookingStatus" autoWidth header="Status">
                        {({item}) => item.bookingStatus === "CONFIRMED" ? "✅" : "❌"}
                    </GridColumn>
                </Grid>
            </div>
        </SplitLayout>
    );
}
