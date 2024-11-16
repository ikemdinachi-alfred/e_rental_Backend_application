package com.alfredTech.e_rental_application.controllers;
import com.alfredTech.e_rental_application.dtos.reponse.Response;
import com.alfredTech.e_rental_application.services.serviceInterface.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addNewItem(
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "itemType", required = false) String itemType,
            @RequestParam(value = "itemPrice", required = false) BigDecimal itemPrice,
            @RequestParam(value = "itemDescription", required = false) String itemDescription
    ) {

        if (photo == null || photo.isEmpty() || itemType == null || itemType.isBlank() || itemPrice == null || itemType.isBlank()) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(photo, itemType,itemPrice)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        Response response = itemService.addNewItem(photo,name, itemType, itemPrice, itemDescription);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/all-available-item")
    public ResponseEntity<Response> getAvailableItems() {
        Response response = itemService.getAllAvailableItems();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<Response> getAllItems(){
        Response response = itemService.getAllItems();
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @GetMapping("/types")
    public List<String> getItemTypes(){
        return itemService.getAllItemType();
    }

    @GetMapping("/item-by-id/{itemId}")
    public ResponseEntity<Response> getItemById(@PathVariable Long itemId){
        Response response = itemService.getItemById(itemId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/available-items-by-date-and-type")
    public ResponseEntity<Response> getAvailableItemsByDateAndType(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam(required = false) String itemType
    ) {
        if (checkInDate == null || itemType == null || itemType.isBlank() || checkOutDate == null) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(checkInDate, itemType,checkOutDate)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        Response response = itemService.getAvailableItemsByDataAndType(checkInDate, checkOutDate, itemType);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/update/{itemId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateRoom(@PathVariable Long itemId,
                                               @RequestParam(value = "photo", required = false) MultipartFile photo,
                                               @RequestParam(value = "name",required = false) String name,
                                               @RequestParam(value = "itemType", required = false) String itemType,
                                               @RequestParam(value = "itemPrice", required = false) BigDecimal itemPrice,
                                               @RequestParam(value = "itemDescription", required = false) String itemDescription

    ) {
        Response response = itemService.updateItem(itemId,name,itemDescription,itemType ,itemPrice, photo);

        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @DeleteMapping("/delete/{itemId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteItem(@PathVariable Long itemId) {
        Response response = itemService.deleteItem(itemId);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

}
