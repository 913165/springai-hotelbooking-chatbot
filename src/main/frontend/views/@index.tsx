
import {useEffect, useState} from "react";
import {nanoid} from "nanoid";
import {AssistantService, BookingService} from 'Frontend/generated/endpoints.js';
import {GridColumn} from "@vaadin/react-components/GridColumn";
import {Grid} from "@vaadin/react-components/Grid";
import {SplitLayout} from "@vaadin/react-components/SplitLayout";
import HotelBookingDetails from "../generated/com/example/application/services/HotelBookingDetails";
import Message, {MessageItem} from "../components/Message";
import MessageList from "Frontend/components/MessageList";
import {MessageInput} from "@vaadin/react-components";

export default function Index() {
    const [working, setWorking] = useState(false);
    const [bookings, setBookings] = useState<HotelBookingDetails[]>([]);
    const [chatId, setChatId] = useState(nanoid());
    const [messages, setMessages] = useState<MessageItem[]>([{
        role: 'assistant',
        content: 'Welcome to Great Hotel ! How can I help you?'
    }]);
    useEffect(() => {
        // Update bookings when we have received the full response
        if (!working) {
            BookingService.getBookings().then(setBookings);
        }
    }, [working]);

    function addMessage(message: MessageItem) {
        setMessages(messages => [...messages, message]);
    }

    function appendToLatestMessage(chunk: string) {
        setMessages(messages => {
            const latestMessage = messages[messages.length - 1];
            latestMessage.content += chunk;
            return [...messages.slice(0, -1), latestMessage];
        });
    }
    async function sendMessage(message: string) {
        setWorking(true);
        addMessage({
            role: 'user',
            content: message
        });
        let first = true;
        AssistantService.chat(chatId, message)
            .onNext(token => {
                if (first && token) {
                    addMessage({
                        role: 'assistant',
                        content: token
                    });

                    first = false;
                } else {
                    appendToLatestMessage(token);
                }
            })
            .onError(() => setWorking(false))
            .onComplete(() => setWorking(false));
    }

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
            <div className="flex flex-col gap-m p-m box-border h-full" style={{width: '30%'}}>
                <h3>Great Hotel support</h3>
                <MessageList messages={messages} className="flex-grow overflow-scroll"/>
                <MessageInput onSubmit={e => sendMessage(e.detail.value)} className="px-0" disabled={working}/>
            </div>
        </SplitLayout>
    );
}
