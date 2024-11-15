package com.alfredTech.e_rental_application.services.serviceInterface;

import com.alfredTech.e_rental_application.dtos.reponse.Response;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ItemService {

    Response addNewItem(MultipartFile photo, String itemName, String itemType, BigDecimal itemPrice, String description);
    List<String> getAllItemType();
    Response getAllItemTypes();
    Response updateItem(Long itemId,  String itemName, String description, String itemType, BigDecimal itemPrice,
                        MultipartFile photo);
    Response getItemById(Long itemId);
    Response getAvailableItemsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate,String itemType );
    Response allAvailableItems();
    Response getAllItems();
    Response deleteItem(Long roomId);

}
