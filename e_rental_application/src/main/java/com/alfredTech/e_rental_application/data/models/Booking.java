package com.alfredTech.e_rental_application.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "user_bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Check in date is required")
    private LocalDate checkInDate;
    @Future(message = "Check out date must be in the future")
    private LocalDate checkOutDate;

    @Min(value = 1, message = "Number of item must not be less than 1")
    private Long quantity;

    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "user_id")
    private User user;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", check in Date=" + checkInDate +
                ", check out Date=" + checkOutDate +
                ", quantity=" + quantity +
                ", booking Confirmation Code='" + bookingConfirmationCode + '\'' +
                '}';
    }
}
