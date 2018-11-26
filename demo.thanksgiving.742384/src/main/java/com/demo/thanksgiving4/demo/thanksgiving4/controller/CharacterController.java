package com.demo.thanksgiving4.demo.thanksgiving4.controller;

import com.demo.thanksgiving4.demo.thanksgiving4.entity.Character;
import com.demo.thanksgiving4.demo.thanksgiving4.service.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/character/gen")
public class CharacterController {

    private static Logger LOGGER = LoggerFactory.getLogger(CharacterController.class);
    public final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/getCharacters")
    public Iterable<Character> getAllCharacters() {
        return this.characterService.getAllCharacters();
    }

    @GetMapping("/getCharacters/{characterId}")
    public Character getCharacterById(@PathVariable("characterId") Long characterId) {
        LOGGER.info("Is characterId available ? : " + this.characterService.existsById(characterId));
        return this.characterService.getCharactersById(characterId);
    }

    @PostMapping("/createCharacter")
    public Character create(@RequestBody Character character) {
        LOGGER.info("Character is available : " + character.getCharacterId());
        if ((character.getCharacterId() != null) && (this.characterService.existsById(character.getCharacterId()))) {
            return this.characterService.createCharacter(character);
        }
        return this.characterService.createCharacter(character);
    }

    @PutMapping("/updateCharacter")
    public Character update(@RequestBody Character character) {
        return this.characterService.updateCharacter(character);
    }

    @PostMapping("/delete/Character/{characterId}")
    public void deleteWithWrongMapping(@PathVariable("characterId") Long characterId) {
        this.characterService.deleteById(characterId);
    }

    @DeleteMapping("/deleteCharacter/{characterId}")
    public void deleteCharacter(@PathVariable("characterId") Long characterId) {
        this.characterService.deleteById(characterId);
    }
}