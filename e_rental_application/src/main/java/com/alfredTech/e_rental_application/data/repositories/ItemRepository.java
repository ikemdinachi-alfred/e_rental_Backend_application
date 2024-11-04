package com.alfredTech.e_rental_application.data.repositories;

import com.alfredTech.e_rental_application.data.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

  @Query("SELECT DISTINCT i.itemType FROM Item  i")
  List<String> findDistinctItemTypes();


  @Query("SELECT i FROM Item i WHERE i.itemType LIKE %:itemType% AND i.id NOT IN (SELECT bk.item.id FROM Booking bk WHERE" +
   "(bk.bookingDate <= :returnDate) AND (bk.returnDate >= :bookingDate))")
  List<Item> findAvailableItemByDateAndTypes(LocalDate bookingDate, LocalDate returnDate, String itemType);


  @Query("SELECT i FROM Item i WHERE i.id NOT IN (SELECT b.item.id FROM Booking b)")
  List<Item> getAllAvailableItem();


}