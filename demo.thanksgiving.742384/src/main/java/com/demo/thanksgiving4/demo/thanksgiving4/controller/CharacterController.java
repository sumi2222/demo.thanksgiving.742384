package com.demo.thanksgiving4.demo.thanksgiving4.controller;

import com.demo.thanksgiving4.demo.thanksgiving4.entity.Character;
import com.demo.thanksgiving4.demo.thanksgiving4.service.CharacterService;
import com.demo.thanksgiving4.demo.thanksgiving4.utility.Character_enum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Iterable<Character> getAllCharacters() { return this.characterService.getAllCharacters(); }

    @GetMapping("/getCharacter/{characterId}")
    public Character getCharacterById(@PathVariable("characterId") Long characterId) {
        LOGGER.info("Is characterId available ? : " + this.characterService.existsById(characterId));
        return this.characterService.getCharactersById(characterId);
    }

    //@ResponseStatus(value = HttpStatus.PROCESSING, reason = "CharacterType cannot be other than WARRIOR or WIZARD or ROGUE or ARCHER")
    @PostMapping("/createCharacter/{characterName}/{characterType}")
    public ResponseEntity<Character> createCharacter(@PathVariable("characterName") String characterName,@PathVariable("characterType") String characterType ) {
        Character character = new Character();
        characterType = characterType.toUpperCase().trim();
        if (characterType.equalsIgnoreCase(Character_enum.WARRIOR.toString()) || characterType.equalsIgnoreCase(Character_enum.WIZARD.toString()) ||
                characterType.equalsIgnoreCase(Character_enum.ARCHER.toString())  || characterType.equalsIgnoreCase(Character_enum.ROGUE.toString())) {
            character.setCharacterName(characterName);
            character.setCharacterType(characterType);
            LOGGER.info("New Character-Id is : " + character.getCharacterId());
            return ResponseEntity.ok(this.characterService.createCharacter(character));

        }else{
            character.setCharacterType("CharacterType cannot be other than WARRIOR or WIZARD or ROGUE or ARCHER");
            return ResponseEntity.badRequest().body(character);
        }
    }

    @PutMapping("/updateCharacter")
    public Character updateCharacter(@RequestBody Character character) {
        return this.characterService.updateCharacter(character);
    }

    @DeleteMapping("/deleteCharacter/{characterId}")
    public void deleteCharacter(@PathVariable("characterId") Long characterId) {
        this.characterService.deleteById(characterId);
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "CharacterType cannot be other than WARRIOR or WIZARD or ROGUE or ARCHER")
    public class WrongCharacterTypeException extends Exception {
    }
}