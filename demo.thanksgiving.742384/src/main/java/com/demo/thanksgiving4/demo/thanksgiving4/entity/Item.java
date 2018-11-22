package com.demo.thanksgiving4.demo.thanksgiving4.entity;
//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;


//@Table(name = "ITEM", uniqueConstraints = {@UniqueConstraint(columnNames = "ITEM_ID")})
@Entity(name="ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ITEM_ID")
    private Long itemId;

    @Column(name="ITEM_NAME")
    private String itemName;

    @Column(name="CHARACTER_ID", insertable = false, updatable = false, nullable = false)
    private Long characterId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "CHARACTER_ID")
    private Character character;

    public Long getItemId() {
        return itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemId(Long itemId) { this.itemId = itemId; }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public Long getCharacterId() { return characterId; }
    public void setCharacterId(Long characterId) { this.characterId = characterId; }
    public Character getCharacter() { return character; }
    public void setCharacter(Character character) { this.character = character; }


    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", characterId=" + characterId +
                '}';
    }
}
