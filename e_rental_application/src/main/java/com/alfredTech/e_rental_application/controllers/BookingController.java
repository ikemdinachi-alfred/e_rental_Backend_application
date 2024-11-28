package com.alfredTech.e_rental_application.controllers;
import com.alfredTech.e_rental_application.data.models.Booking;
import com.alfredTech.e_rental_application.dtos.reponse.Response;
import com.alfredTech.e_rental_application.services.serviceInterface.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping("/book-item/{itemId}/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> saveBookings(@PathVariable Long itemId,
                                                 @PathVariable Long userId,
                                                 @RequestBody Booking bookingRequest) {


        Response response = bookingService.saveBooking(itemId, userId, bookingRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllBookings() {
        Response response = bookingService.getAllBookings();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-by-confirmation-code/{confirmationCode}")
    public ResponseEntity<Response> getBookingByConfirmationCode(@PathVariable String confirmationCode) {
        Response response = bookingService.findBookingByConfirmationCode(confirmationCode);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/cancel/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> cancelBooking(@PathVariable Long bookingId) {
        Response response = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
