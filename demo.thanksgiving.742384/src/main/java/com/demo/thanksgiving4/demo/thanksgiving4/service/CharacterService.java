package com.demo.thanksgiving4.demo.thanksgiving4.service;

import com.demo.thanksgiving4.demo.thanksgiving4.entity.Character;
import com.demo.thanksgiving4.demo.thanksgiving4.repository.CharacterRepository;
import com.demo.thanksgiving4.demo.thanksgiving4.utility.BuildCharacterEntityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private static Logger LOGGER = LoggerFactory.getLogger(CharacterService.class);

    @Autowired
    public CharacterRepository characterRepository;

    @Autowired
    public BuildCharacterEntityHelper buildCharacterEntityHelper;


    public Iterable<Character> getAllCharacters() {
        return this.characterRepository.findAll();
    }

    public Character getCharactersById(Long characterId) {
        LOGGER.info("Is characterId available ? : " + this.characterRepository.existsById(characterId));
        return this.characterRepository.findById(characterId).get();
    }

    public Character createCharacter(Character character) {
        Character buildCharacter  = buildCharacterEntityHelper.buildCharacterUsingType(character);
        buildCharacter.setCharacterName(character.getCharacterName());
        buildCharacter.setCharacterType(character.getCharacterType());
        return this.characterRepository.save(character);
    }

    public Character updateCharacter(Character character) {
        Character buildCharacter  = buildCharacterEntityHelper.buildCharacterUsingType(character);
        buildCharacter.setCharacterName(character.getCharacterName());
        buildCharacter.setCharacterType(character.getCharacterType());
        return this.characterRepository.save(buildCharacter);
    }

    public void deleteWithWrongMapping(Long characterId) {
        this.characterRepository.deleteById(characterId);
    }

    public void deleteById(Long characterId) {
        this.characterRepository.deleteById(characterId);
    }

    public boolean existsById(Long characterId) {
        return this.characterRepository.existsById(characterId);
    }
}
