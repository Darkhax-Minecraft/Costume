package net.darkhax.costume;

import net.minecraft.item.Item;

public enum CostumeType {
    
    EVIL_CLOWN("evil_clown"),
    RAINBOW_SHEEP("rainbow_sheep"),
    MELON("melon"),
    DEAD_GIRL("dead_girl");
    
    private final String name;
    private Item[] costumeItems = new Item[4];
    
    CostumeType(String name) {
        
        this.name = name;
    }

    public Item[] getCostumeItems () {
        
        return costumeItems;
    }

    public String getName () {
        
        return name;
    }
}
