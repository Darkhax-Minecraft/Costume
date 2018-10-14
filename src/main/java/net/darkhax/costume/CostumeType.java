package net.darkhax.costume;

import net.darkhax.bookshelf.lib.Constants;
import net.minecraft.item.Item;

public enum CostumeType {
    
    EVIL_CLOWN("evil_clown"),
    RAINBOW_SHEEP("rainbow_sheep"),
    MELON("melon"),
    DEAD_GIRL("dead_girl"),
    QUESTION("question"),
    DEAD_ZOMBIE("dead_zombie");
    
    private final String name;
    private final Item[] costumeItems = new Item[4];
    
    CostumeType(String name) {
        
        this.name = name;
    }
    
    public Item[] getCostumeItems () {
        
        return this.costumeItems;
    }
    
    public String getName () {
        
        return this.name;
    }
    
    public static CostumeType getRandom() {
        
        return CostumeType.values()[Constants.RANDOM.nextInt(CostumeType.values().length)];
    }
}
