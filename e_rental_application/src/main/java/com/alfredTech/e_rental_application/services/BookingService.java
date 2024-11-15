package com.alfredTech.e_rental_application.services;

import com.alfredTech.e_rental_application.data.models.Booking;
import com.alfredTech.e_rental_application.dtos.reponse.Response;

public interface BookingService {

    Response saveBooking(Long roomId, Long UserId, Booking bookingRequest);
    Response findBookingByConfirmationCode(String confirmationCode);
    Response getAllBookings();
    Response cancelBooking(Long id);
}

