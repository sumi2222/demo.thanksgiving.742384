package com.demo.thanksgiving4.demo.thanksgiving4.controller;
import com.demo.thanksgiving4.demo.thanksgiving4.repository.CharacterRepository;
import com.demo.thanksgiving4.demo.thanksgiving4.repository.ItemRepository;
import com.demo.thanksgiving4.demo.thanksgiving4.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.thanksgiving4.demo.thanksgiving4.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/object")
public class ItemController {

    private static Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    public final ItemRepository itemRepository;

    @Autowired
    public final CharacterService characterService=null;

    public ItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @GetMapping("/getItems")
    public Iterable<Item> getAllItems() {
        return this.itemRepository.findAll();
    }

    @GetMapping("/getItem/{itemId}")
    public Item getItem(@PathVariable("itemId") Long itemId) {
        LOGGER.info("Is itemId available ? : " + this.itemRepository.existsById(itemId));
        return this.itemRepository.findById(itemId).get();
    }

    @GetMapping("/getItemByName/{itemName}")
    public ArrayList<Item> getSpecificItems(@PathVariable("itemName") String itemName) {
        LOGGER.info("Is itemId available ? : " + this.itemRepository.findByName(itemName));
        ArrayList<Item> itemList = new ArrayList<>();
        Iterable<Item> allItems = this.itemRepository.findAll();
        for(Item item : allItems ){
            if(item.getItemName().equalsIgnoreCase(itemName.trim())){
                itemList.add(item);
            }
        }
        return  itemList;
    }

    @PostMapping ("/createItem")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        if ((item.getItemId() != null) && (this.itemRepository.existsById(item.getItemId()))){
            return  ResponseEntity.status(HttpStatus.OK).body(this.itemRepository.save(item));
        }
        LOGGER.info("====================ITEM is  =====================================: " +item.toString());
        return ResponseEntity.status(HttpStatus.OK).body(this.itemRepository.save(item));
    }

    @PutMapping("/updateItem")
    public Item updateItem(@RequestBody Item item){
        return this.itemRepository.save(item);
    }

  /*  @PostMapping("/delete/Item/{itemId}")
    public void deleteWithWrongMapping(@PathVariable("itemId") Long itemId){
        this.itemRepository.deleteById(itemId);
    }*/

    @DeleteMapping("/deleteItem/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId){
        this.itemRepository.deleteById(itemId);
    }

}