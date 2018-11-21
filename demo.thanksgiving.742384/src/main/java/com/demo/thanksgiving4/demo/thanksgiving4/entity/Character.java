package com.demo.thanksgiving4.demo.thanksgiving4.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/*The values for int, wis, cha, str, des returned are generated at Random and all fall between 8 and 18.
If class = Warrior then the highest number should be assigned to Str and the lowest to Int,
if the class = Archer then the highest number goes to Dex and lowested to Cha,
if the class is Wizard then the highest number goes to Int and the lowest to Str,
if the class is Rogue then highest goes to Cha and the lowest goes to Str.
IF any other class is provided it should return an error.*/


@Entity(name="CHARACTER")
public class Character implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CHARACTER_ID")
    private Long characterId;

    @Column(name="CHARACTER_NAME")
    private String characterName;

    @Column(name="CHARACTER_TYPE")
    private String characterType;

    @Column(name="CHARACTER_INT")
    private Integer characterInt;

    @Column(name="CHARACTER_WIS")
    private Integer characterWis;

    @Column(name="CHARACTER_CHA")
    private Integer characterCha;

    @Column(name="CHARACTER_STR")
    private Integer characterStr;

    @Column(name="CHARACTER_DEX")
    private Integer characterDex;

    @Column(name="CHARACTER_CON")
    private Integer characterCon;

    @Column(name="CHARACTER_LOCATION")
    private Integer characterLocation;

    @Column(name="CHARACTER_HITPOINTS")
    private Integer characterHitpoints;

    //@JsonIgnore
    @Fetch(FetchMode.JOIN)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="ITEM_INNER TABLE", joinColumns={@JoinColumn(name="CHARACTER_ID", referencedColumnName="CHARACTER_ID")}
            , inverseJoinColumns={@JoinColumn(name="ITEM_ID", referencedColumnName="ITEM_ID")})
    private Collection<Item> items;

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }

    public Integer getCharacterInt() {
        return characterInt;
    }

    public void setCharacterInt(Integer characterInt) {
        this.characterInt = characterInt;
    }

    public Integer getCharacterWis() {
        return characterWis;
    }

    public void setCharacterWis(Integer characterWis) {
        this.characterWis = characterWis;
    }

    public Integer getCharacterCha() {
        return characterCha;
    }

    public void setCharacterCha(Integer characterCha) {
        this.characterCha = characterCha;
    }

    public Integer getCharacterStr() {
        return characterStr;
    }

    public void setCharacterStr(Integer characterStr) {
        this.characterStr = characterStr;
    }

    public Integer getCharacterDex() {
        return characterDex;
    }

    public void setCharacterDex(Integer characterDex) {
        this.characterDex = characterDex;
    }

    public Integer getCharacterCon() {
        return characterCon;
    }

    public void setCharacterCon(Integer characterCon) {
        this.characterCon = characterCon;
    }

    public Integer getCharacterLocation() {
        return characterLocation;
    }

    public void setCharacterLocation(Integer characterLocation) {

        this.characterLocation = characterLocation;
    }

    public Integer getCharacterHitpoints() {
        return characterHitpoints;
    }

    public void setCharacterHitpoints(Integer characterHitpoints) {
        this.characterHitpoints = characterHitpoints;
    }

    public Collection<Item> getCharacterInventory() {
        return items;
    }

    public void setCharacterInventory(Collection<Item> characterInventory) {
        this.items = characterInventory;
    }

    @Override
    public String toString() {
            return "Character{" +
            "characterId=" + characterId +
            ", characterName='" + characterName + '\'' +
            ", characterType='" + characterType + '\'' +
            ", characterInt=" + characterInt +
            ", characterWis=" + characterWis +
            ", characterCha=" + characterCha +
            ", characterStr=" + characterStr +
            ", characterDex=" + characterDex +
            ", characterCon=" + characterCon +
            ", characterLocation=" + characterLocation +
            ", characterHitpoints=" + characterHitpoints +
            ", characterInventory=" + items +
            '}';
    }

}





