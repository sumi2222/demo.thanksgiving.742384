package com.demo.thanksgiving4.demo.thanksgiving4.repository;

/*The values for int, wis, cha, str, des returned are generated at Random and all fall between 8 and 18.
If class = Warrior then the highest number should be assigned to Str and the lowest to Int,
if the class = Archer then the highest number goes to Dex and lowested to Cha,
if the class is Wizard then the highest number goes to Int and the lowest to Str,
if the class is Rogue then highest goes to Cha and the lowest goes to Str.
IF any other class is provided it should return an error.*/


import com.demo.thanksgiving4.demo.thanksgiving4.entity.Character;
import com.demo.thanksgiving4.demo.thanksgiving4.utility.Character_enum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Character, Long> {

}
