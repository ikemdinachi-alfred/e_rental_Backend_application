package com.alfredTech.e_rental_application.utils;
import com.alfredTech.e_rental_application.data.models.Booking;
import com.alfredTech.e_rental_application.data.models.Item;
import com.alfredTech.e_rental_application.data.models.User;
import com.alfredTech.e_rental_application.dtos.requests.BookingDTO;
import com.alfredTech.e_rental_application.dtos.requests.ItemDTO;
import com.alfredTech.e_rental_application.dtos.requests.UserDTO;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {
    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    private static final SecureRandom secureRandom = new SecureRandom();


    public static String generateRandomConfirmationCode(int length) {
        StringBuilder randomCode = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomInt = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomInt);
            randomCode.append(randomChar);
        }
        return randomCode.toString();
    }

    public static UserDTO mapUserModelToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(userDTO.getRole());
        return userDTO;
    }


    public static ItemDTO mapItemModelToItemDTO(Item item) {
       ItemDTO itemDTO = new ItemDTO();
       itemDTO.setId(item.getId());
       itemDTO.setItemName(item.getName());
       itemDTO.setItemType(item.getItemType());
       itemDTO.setItemPrice(item.getItemPrice());
       itemDTO.setItemPhotoUrl(item.getItemPhotoUrl());
       itemDTO.setItemDescription(item.getItemDescription());
       return itemDTO;
    }

    public static BookingDTO mapBookingModelToBookingDTO(Booking booking, boolean mapUser) {
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setQuantity(bookingDTO.getQuantity());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());
        return bookingDTO;
    }
    public static ItemDTO mapItemModelToItemDTOPlusBookings(Item item) {
        if (item == null) {
            return null;
        }
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setItemType(item.getItemType());
        itemDTO.setItemPrice(item.getItemPrice());
        itemDTO.setItemPhotoUrl(itemDTO.getItemPhotoUrl());
        itemDTO.setItemDescription(itemDTO.getItemDescription());
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        if (item.getBookings() != null) {
            for (Booking booking : item.getBookings()) {
                bookingDTOs.add(Utils.mapBookingModelToBookingDTO(booking, true));
            }

        }
            itemDTO.setBookings(bookingDTOs);

        return itemDTO;

    }
        public static BookingDTO mapBookingModelToBookingDTOPlusBookedItems(Booking booking, boolean mapUser) {
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setQuantity(bookingDTO.getQuantity());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());
        if (mapUser) {
            bookingDTO.setUser(Utils.mapUserModelToUserDTO(booking.getUser()));
        }
        if (booking.getItem() != null) {
            ItemDTO itemDTO = new ItemDTO();

            itemDTO.setId(booking.getItem().getId());
            itemDTO.setItemType(booking.getItem().getItemType());
            itemDTO.setItemPrice(booking.getItem().getItemPrice());
            itemDTO.setItemPhotoUrl(booking.getItem().getItemPhotoUrl());
            itemDTO.setItemDescription(booking.getItem().getItemDescription());
            bookingDTO.setItem(itemDTO);
        }
        return bookingDTO;
    }

    public static UserDTO mapUserModelToUserDTOPlusUserBookingAndItem(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        if (!user.getBookings().isEmpty()) {
            userDTO.setBookings(user.getBookings().stream().map(booking -> mapBookingModelToBookingDTO(booking,false)).collect(Collectors.toList()));
        }
        return userDTO;
    }

    public static List<UserDTO> mapUseListModelToUserListDTO(List<User> users){
        return users.stream().map(Utils::mapUserModelToUserDTO).collect(Collectors.toList());
    }

    public static List<ItemDTO> mapItemListModelToItemListDTO(List<Item> items){
       return items.stream().map(Utils::mapItemModelToItemDTO).collect(Collectors.toList());
    }


    public static List<BookingDTO> mapBookingListModelToBookingListDTO(List<Booking> bookings) {
        List<BookingDTO> bookingDTOs = new ArrayList<>();

        if (bookings != null) {
            for (Booking booking : bookings) {
                bookingDTOs.add(Utils.mapBookingModelToBookingDTO(booking, true));
            }
        }

        return bookingDTOs;
    }

}
