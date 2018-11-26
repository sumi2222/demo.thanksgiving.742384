package com.demo.thanksgiving4.demo.thanksgiving4.controller;
import com.demo.thanksgiving4.demo.thanksgiving4.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.thanksgiving4.demo.thanksgiving4.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/object")
public class ItemController {

    private static Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    public final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @GetMapping("/getItems")
    public Iterable<Item> getAllItems() {
        return this.itemRepository.findAll();
    }

    @GetMapping("/getItems/{itemId}")
    public Item getAllItems(@PathVariable("itemId") Long itemId) {
        LOGGER.info("Is itemId available ? : " + this.itemRepository.existsById(itemId));
        return this.itemRepository.findById(itemId).get();
    }

    @PostMapping ("/createItem")
    public ResponseEntity<Item> create(@RequestBody Item item) {
       LOGGER.info("Item is available : " + item.getItemId());
        if ((item.getItemId() != null) && (this.itemRepository.existsById(item.getItemId()))){

            return  ResponseEntity.status(HttpStatus.OK).body(this.itemRepository.save(item));
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.itemRepository.save(item));
    }

    @PutMapping("/updateItem")
    public Item update(@RequestBody Item item){
        return this.itemRepository.save(item);
    }

    @PostMapping("/delete/Item/{itemId}")
    public void deleteWithWrongMapping(@PathVariable("itemId") Long itemId){
        this.itemRepository.deleteById(itemId);
    }

    @DeleteMapping("/deleteItem/{itemId}")
    public void delete(@PathVariable("itemId") Long itemId){
        this.itemRepository.deleteById(itemId);
    }

}