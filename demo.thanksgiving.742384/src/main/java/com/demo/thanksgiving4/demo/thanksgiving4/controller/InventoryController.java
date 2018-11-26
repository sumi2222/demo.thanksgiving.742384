package com.demo.thanksgiving4.demo.thanksgiving4.controller;

import com.demo.thanksgiving4.demo.thanksgiving4.entity.Character;
import com.demo.thanksgiving4.demo.thanksgiving4.entity.Item;
import com.demo.thanksgiving4.demo.thanksgiving4.entity.Room;
import com.demo.thanksgiving4.demo.thanksgiving4.repository.ItemRepository;
import com.demo.thanksgiving4.demo.thanksgiving4.repository.RoomRepository;
import com.demo.thanksgiving4.demo.thanksgiving4.restEntity.CharacterRestEntity;
import com.demo.thanksgiving4.demo.thanksgiving4.service.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private static Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);
    public final CharacterService characterService;
    public final ItemRepository itemRepository;
    public final RoomRepository roomRepository;

    public InventoryController(CharacterService characterService, ItemRepository itemRepository, RoomRepository roomRepository) {
        this.characterService = characterService;
        this.itemRepository  = itemRepository;
        this.roomRepository  = roomRepository;
}
    @GetMapping("/getInventories")
    public Iterable<Character> getAllCharacters() { return this.characterService.getAllCharacters(); }

    @GetMapping("/getInventory/{characterId}")
    public Character getInventoryById(@PathVariable("characterId") Long characterId) {
        LOGGER.info("Is characterId available ? : " + this.characterService.existsById(characterId));
        return this.characterService.getCharactersById(characterId);
    }

    //@ResponseStatus(value = HttpStatus.PROCESSING, reason = "CharacterType cannot be other than WARRIOR or WIZARD or ROGUE or ARCHER")
    @PostMapping("/createInventoryItem/{characterId}/{itemId}")
    public ResponseEntity<Character> createInventoryItem(@PathVariable("characterId") Long characterId, @PathVariable("itemId") Long itemId) {
        Character character = null;
        if(this.characterService.existsById(characterId) && this.itemRepository.existsById(itemId)){
            character = this.characterService.getCharactersById(characterId);
            Item item = this.itemRepository.findById(itemId).get();
            LOGGER.info("=============================Character is available : " + this.characterService.existsById(characterId));
            LOGGER.info("=============================Item is available : " +this.itemRepository.findById(itemId).get().toString() );
            List<Item> listItem = null;
            if(character.getItemInventory() == null){
                listItem = new ArrayList<>();
            }else {
                listItem = character.getItemInventory();
            }
            //List<Item> listItem1 = listItem;
           // if(! listItem.stream().anyMatch(x->x.getItemName().equals( item.getItemName()))) {
                listItem.add(item);
                LOGGER.info("=============================Item is available : " + listItem.toString());
                character.setItemInventory(listItem);
                return ResponseEntity.ok(this.characterService.updateCharacter(character));
        }else{
            return ResponseEntity.badRequest().body(character);
        }
    }

    @PostMapping("/createInventoryRoom/{characterId}/{roomId}")
    public ResponseEntity<CharacterRestEntity> createInventoryRoom(@PathVariable("characterId") Long characterId, @PathVariable("roomId") Long roomId) {
        Character character = null;
        CharacterRestEntity restCharacter = new CharacterRestEntity();
        if(this.characterService.existsById(characterId) && this.roomRepository.existsById(roomId)){
            character = this.characterService.getCharactersById(characterId);
            Room room = this.roomRepository.findById(roomId).get();
            LOGGER.info("=============================Character is available : " + this.characterService.existsById(characterId));
            LOGGER.info("=============================Item is available : " +this.roomRepository.findById(roomId).get().toString() );
            character.setCharacterLocation(roomId);
            this.characterService.updateCharacter(character);
            restCharacter= this.characterService.buildRestCharacterEntityWithRoom(character, room);
            LOGGER.info("=============================Character is available : " + restCharacter.toString());
            return ResponseEntity.ok(restCharacter);
        }else{
            return ResponseEntity.badRequest().body(restCharacter);
        }
    }

    @PutMapping("/updateInventory")
    public Character updateInventory(@RequestBody Character character) {
        return this.characterService.updateCharacter(character);
    }

    @DeleteMapping("/deleteInventory/{characterId}")
    public void deleteInventory(@PathVariable("characterId") Long characterId) {
        this.characterService.deleteById(characterId);
    }
}

