package com.alfredTech.e_rental_application.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String itemType;
    private BigDecimal itemPrice;
    private String itemPhotoUrl;
    private String itemDescription;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemPhotoUrl='" + itemPhotoUrl + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
