package com.demo.thanksgiving4.demo.thanksgiving4.queryHelper;

import com.demo.thanksgiving4.demo.thanksgiving4.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryQueryHelper {

    @Query("select i from ITEM i where i.itemId=?1")
    List<Item> findByFirstnameEndsWith(Long itemId);

}

