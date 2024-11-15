package com.alfredTech.e_rental_application.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class ItemDTO {
    private Long id;
    private String itemName;
    private String itemType;
    private BigDecimal itemPrice;
    private String itemPhotoUrl;
    private String itemDescription;
    private List<BookingDTO> bookings;


}
