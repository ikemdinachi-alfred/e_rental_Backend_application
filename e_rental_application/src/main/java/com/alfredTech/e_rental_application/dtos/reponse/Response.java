package com.alfredTech.e_rental_application.dtos.reponse;

import com.alfredTech.e_rental_application.dtos.requests.BookingDTO;
import com.alfredTech.e_rental_application.dtos.requests.ItemDTO;
import com.alfredTech.e_rental_application.dtos.requests.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private int statusCode;
    private String message;
    private String token;
    private String role;
    private String expirationTime;
    private String bookingConfirmationCode;

    private UserDTO user;
    private ItemDTO itemDTO;
    private BookingDTO booking;
    private List<UserDTO> userList;
    private List<ItemDTO> itemDTOList;
    private List<BookingDTO> bookingList;
}
