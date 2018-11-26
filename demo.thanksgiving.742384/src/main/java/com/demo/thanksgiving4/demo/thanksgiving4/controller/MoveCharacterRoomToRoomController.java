package com.demo.thanksgiving4.demo.thanksgiving4.controller;
import com.demo.thanksgiving4.demo.thanksgiving4.entity.Character;
import com.demo.thanksgiving4.demo.thanksgiving4.entity.Room;
import com.demo.thanksgiving4.demo.thanksgiving4.repository.RoomRepository;
import com.demo.thanksgiving4.demo.thanksgiving4.restEntity.CharacterRestEntity;
import com.demo.thanksgiving4.demo.thanksgiving4.service.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/move")
public class MoveCharacterRoomToRoomController {

    private static Logger LOGGER = LoggerFactory.getLogger(com.demo.thanksgiving4.demo.thanksgiving4.controller.MoveCharacterRoomToRoomController.class);
    private final RoomRepository roomRepository;
    private final CharacterService characterService;

    public MoveCharacterRoomToRoomController(RoomRepository roomRepository, CharacterService characterService) {
        this.roomRepository = roomRepository;
        this.characterService = characterService;
    }

    @GetMapping("/getMovedCharacterById/{characterId}")
    public ResponseEntity<CharacterRestEntity> getMovedCharacterById(@PathVariable("characterId") Long characterId) {
        Character character = this.characterService.getCharactersById(characterId);
        CharacterRestEntity restCharacter = new CharacterRestEntity();
        Long location = character.getCharacterLocation();
        Room room = this.roomRepository.findById(location).get();
        restCharacter = this.characterService.buildRestCharacterEntityWithRoom(character, room);
        LOGGER.info("=================== Rest Character  =====================================: " +restCharacter.toString());
        return ResponseEntity.status(HttpStatus.OK).body(restCharacter);
    }

    @PostMapping("/{characterId}/{roomId}")
    public ResponseEntity<CharacterRestEntity> moveCharacterToSpecificRoom(@PathVariable("characterId") Long characterId, @PathVariable("roomId") Long roomId) {
        Character character = this.characterService.getCharactersById(characterId);
        CharacterRestEntity restCharacter = new CharacterRestEntity();
        Long location = character.getCharacterLocation();
        Room room = this.roomRepository.findById(location).get();

        if ((character.getCharacterId() != null) && (room.getRoomId() != null)) {

            Long[] roomExits = room.getRoomExits();
            Integer hitpoint = this.characterService.getCharactersById(characterId).getCharacterHitpoints();
            boolean roomExitAvailable = false;
            for (int i = 0; i < roomExits.length; i++) {
                if (roomExits[i].equals(roomId)){
                    roomExitAvailable = true;
                    break;
                }
            }

            if ((roomExitAvailable== true) && (hitpoint>=1)) {
                character.setCharacterLocation(roomId);
                this.characterService.updateCharacter(character);
                restCharacter = this.characterService.buildRestCharacterEntityWithRoom(character, this.roomRepository.findById(roomId).get());
                LOGGER.info("=================== Rest Character  =====================================: " +restCharacter.toString());
                return ResponseEntity.status(HttpStatus.OK).body(restCharacter);
            } else if (!roomExitAvailable) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            } else if (roomExitAvailable && (hitpoint < 1)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
        return ResponseEntity.notFound().build();
    }

}
