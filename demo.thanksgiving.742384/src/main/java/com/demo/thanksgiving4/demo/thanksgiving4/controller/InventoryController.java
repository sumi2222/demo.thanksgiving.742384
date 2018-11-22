package com.demo.thanksgiving4.demo.thanksgiving4.controller;

import com.demo.thanksgiving4.demo.thanksgiving4.entity.Character;
import com.demo.thanksgiving4.demo.thanksgiving4.service.CharacterService;
import com.demo.thanksgiving4.demo.thanksgiving4.utility.Character_enum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class InventoryController {


    private static Logger LOGGER = LoggerFactory.getLogger(CharacterController.class);

    public final CharacterService characterService;

    public InventoryController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/getCharacters")
    public Iterable<Character> getAllCharacters() { return this.characterService.getAllCharacters(); }

    @GetMapping("/getCharacters/{characterId}")
    public Character getCharacterById(@PathVariable("characterId") Long characterId) {
        LOGGER.info("Is characterId available ? : " + this.characterService.existsById(characterId));
        return this.characterService.getCharactersById(characterId);
    }


    //@ResponseStatus(value = HttpStatus.PROCESSING, reason = "CharacterType cannot be other than WARRIOR or WIZARD or ROGUE or ARCHER")
    @PostMapping("/createCharacter")
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        LOGGER.info("Character is available : " + character.getCharacterId());

        if (character.getCharacterType().equalsIgnoreCase(Character_enum.WARRIOR.toString()) || character.getCharacterType().equalsIgnoreCase(Character_enum.WIZARD.toString()) ||
                character.getCharacterType().equalsIgnoreCase(Character_enum.ARCHER.toString())  || character.getCharacterType().equalsIgnoreCase(Character_enum.ROGUE.toString())) {
            return ResponseEntity.ok(this.characterService.createCharacter(character));

        }else{
            character.setCharacterType("CharacterType cannot be other than WARRIOR or WIZARD or ROGUE or ARCHER");
            return ResponseEntity.badRequest().body(character);
        }
    }

    @PutMapping("/updateCharacter")
    public Character update(@RequestBody Character character) {
        return this.characterService.updateCharacter(character);
    }

    @PostMapping("/deleteCharacter/{characterId}")
    public void deleteWithWrongMapping(@PathVariable("characterId") Long characterId) {
        this.characterService.deleteById(characterId);
    }

    @DeleteMapping("/deleteCharacter/{characterId}")
    public void deleteCharacter(@PathVariable("characterId") Long characterId) {
        this.characterService.deleteById(characterId);
    }
}
