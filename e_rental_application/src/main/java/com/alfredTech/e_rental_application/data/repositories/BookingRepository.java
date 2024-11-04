package com.alfredTech.e_rental_application.data.repositories;

import com.alfredTech.e_rental_application.data.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);

}
