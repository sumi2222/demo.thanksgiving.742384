/*The values for int, wis, cha, str, des returned are generated at Random and all fall between 8 and 18.
If class = Warrior then the highest number should be assigned to Str and the lowest to Int,
if the class = Archer then the highest number goes to Dex and lowested to Cha,
if the class is Wizard then the highest number goes to Int and the lowest to Str,
if the class is Rogue then highest goes to Cha and the lowest goes to Str.
IF any other class is provided it should return an error.*/

package com.demo.thanksgiving4.demo.thanksgiving4.utility;

import com.demo.thanksgiving4.demo.thanksgiving4.controller.CharacterController;
import com.demo.thanksgiving4.demo.thanksgiving4.entity.Character;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class BuildCharacterEntityHelper {

    private static Logger LOGGER = LoggerFactory.getLogger(CharacterController.class);
    private static final String CHARRACTER_TYPE_WARRIOR = "WARRIOR";
    private static final String CHARRACTER_TYPE_ARCHER = "ARCHER";
    private static final String CHARRACTER_TYPE_WIZARD = "WIZARD";
    private static final String CHARRACTER_TYPE_ROGUE = "ROGUE ";
    private final RandomNumGen randomNumGen;

    public BuildCharacterEntityHelper(RandomNumGen randomNumGen) {
        this.randomNumGen = randomNumGen;
    }

    public Character buildCharacterUsingType(Character character) {
        int index = 1;

        ArrayList<Integer>  intArrayList = this.randomNumGen.create6RanNum();

        if (Character_enum.WARRIOR.toString().equalsIgnoreCase(character.getCharacterType())) {
            character.setCharacterInt(intArrayList.get(0));
            character.setCharacterStr(intArrayList.get(6));
            character.setCharacterCha(intArrayList.get(index++));
            character.setCharacterDex(intArrayList.get(index++));

        } else if (Character_enum.ARCHER.toString().equalsIgnoreCase(character.getCharacterType())) {
            character.setCharacterCha(intArrayList.get(0));
            character.setCharacterDex(intArrayList.get(6));
            character.setCharacterInt(intArrayList.get(index++));
            character.setCharacterStr(intArrayList.get(index++));

        } else if (Character_enum.WIZARD.toString().equalsIgnoreCase(character.getCharacterType())) {
            character.setCharacterStr(intArrayList.get(0));
            character.setCharacterInt(intArrayList.get(6));
            character.setCharacterCha(intArrayList.get(index++));
            character.setCharacterDex(intArrayList.get(index++));

        } else if (Character_enum.ROGUE.toString().equalsIgnoreCase(character.getCharacterType())) {
            character.setCharacterStr(intArrayList.get(0));
            character.setCharacterCha(intArrayList.get(6));
            character.setCharacterInt(intArrayList.get(index++));
            character.setCharacterDex(intArrayList.get(index++));
        } else
            return (Character) ResponseEntity.status(Integer.
                    parseInt(HttpStatus.EXPECTATION_FAILED.getReasonPhrase().concat(" ########  Un-Identifed Character Type  ######### ")));

        character.setCharacterCon(intArrayList.get(index++));
        character.setCharacterWis(intArrayList.get(index++));
        character.setCharacterLocation(intArrayList.get(index++));
        character.setCharacterHitpoints(character.getCharacterCon()*2);
        return character;
    }

}
