package com.demo.thanksgiving4.demo.thanksgiving4.entity;
//import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name="ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ITEM_ID")
    private Long itemId;

    @Column(name="ITEM_NAME")
    private String itemName;

    public Long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
