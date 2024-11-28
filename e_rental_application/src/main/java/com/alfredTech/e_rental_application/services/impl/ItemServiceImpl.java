package com.alfredTech.e_rental_application.services.impl;

import com.alfredTech.e_rental_application.data.models.Item;
import com.alfredTech.e_rental_application.data.repositories.ItemRepository;
import com.alfredTech.e_rental_application.dtos.reponse.Response;
import com.alfredTech.e_rental_application.dtos.requests.ItemDTO;
import com.alfredTech.e_rental_application.exceptions.OurException;
import com.alfredTech.e_rental_application.services.GoogleCloudStorage;
import com.alfredTech.e_rental_application.services.serviceInterface.ItemService;
import com.alfredTech.e_rental_application.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;


    @Autowired
    private GoogleCloudStorage googleCloudStorage;

    @Override
    public Response addNewItem(MultipartFile photo, String name, String itemType, BigDecimal itemPrice, String description) {
        Response response = new Response();
        try {
            String imageUrl = googleCloudStorage.uploadImage(photo);
            Item item = new Item();
            item.setItemPhotoUrl(imageUrl);
            item.setName(name);
            item.setItemType(itemType);
            item.setItemPrice(itemPrice);
            item.setItemDescription(description);

             Item savedItem = itemRepository.save(item);
            ItemDTO itemDTO = Utils.mapItemModelToItemDTO(savedItem);
            response.setStatusCode(200);
            response.setMessage("Successfully added new room");
            response.setItemDTO(itemDTO);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room  " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<String> getAllItemType() {
        return itemRepository.findDistinctItemTypes();
    }

    @Override
    public Response getAllItemTypes() {

        Response response = new Response();
        try {
            List<Item> itemList = itemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<ItemDTO> itemListDTO = Utils.mapItemListModelToItemListDTO(itemList);
            response.setStatusCode(200);
            response.setMessage("Successfully retrieved all the rooms");
            response.setItemDTOList(itemListDTO);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error retrieving all the rooms " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateItem(Long itemId, String itemName, String description, String itemType, BigDecimal itemPrice, MultipartFile photo) {
        Response response = new Response();
        try {
            String imageUrl = null;
            if (photo != null && !photo.isEmpty()) {
                imageUrl = googleCloudStorage.uploadImage(photo);
            }
            Item item = itemRepository.findById(itemId).orElseThrow(() -> new OurException("Item not found"));
            if (itemType!= null)item.setItemType(itemType);
            if (itemPrice!= null)item.setItemPrice(itemPrice);
            if (description!= null)item.setItemDescription(description);
            if (imageUrl != null) item.setItemPhotoUrl(imageUrl);


            Item updateItem = itemRepository.save(item);
            ItemDTO itemDTO = Utils.mapItemModelToItemDTO(updateItem);


            response.setStatusCode(200);
            response.setMessage("successful");
            response.setItemDTO(itemDTO);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getItemById(Long itemId) {
        Response response = new Response();
        try {
            Item item = itemRepository.findById(itemId).orElseThrow(() -> new OurException("Item not found"));
            ItemDTO itemDTO = Utils.mapItemModelToItemDTO(item);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setItemDTO(itemDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAvailableItemsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate, String itemType) {
        Response response = new Response();

        try {
            List<Item> availableItems = itemRepository.findAvailableItemByDateAndTypes(checkInDate,checkOutDate,itemType);
            List<ItemDTO> itemDTOList = Utils.mapItemListModelToItemListDTO(availableItems);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setItemDTOList(itemDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllAvailableItems() {
        Response response = new Response();

        try {
            List<Item>itemList = itemRepository.getAllAvailableItem();
            List<ItemDTO> itemDTOList = Utils.mapItemListModelToItemListDTO(itemList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setItemDTOList(itemDTOList);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllItems() {
        Response response = new Response();

        try {
            List<Item> itemList = itemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<ItemDTO> itemDTOList = Utils.mapItemListModelToItemListDTO(itemList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setItemDTOList(itemDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteItem(Long itemId) {
        Response response = new Response();

        try {
            itemRepository.findById(itemId).orElseThrow(() -> new OurException("Item Not Found"));
            itemRepository.deleteById(itemId);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }
}
