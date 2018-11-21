package com.demo.thanksgiving4.demo.thanksgiving4.service;

import com.demo.thanksgiving4.demo.thanksgiving4.entity.Character;
import com.demo.thanksgiving4.demo.thanksgiving4.repository.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private static Logger LOGGER = LoggerFactory.getLogger(CharacterService.class);

    public final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Iterable<Character> getAllCharacters() {
        return this.characterRepository.findAll();
    }

    public Character getCharactersById(Long characterId) {
        LOGGER.info("Is characterId available ? : " + this.characterRepository.existsById(characterId));
        return this.characterRepository.findById(characterId).get();
    }

    public Character createCharacter(Character character) {

        return this.characterRepository.save(character);
    }

    public Character updateCharacter(Character character) {
        return this.characterRepository.save(character);
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
