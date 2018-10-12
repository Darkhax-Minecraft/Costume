package net.darkhax.costume;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabCostume extends CreativeTabs {
    
    public CreativeTabCostume() {
        
        super(Costume.MODID);
        this.setBackgroundImageName("item_search.png");
    }
    
    @Override
    public ItemStack createIcon () {
        
        return new ItemStack(Items.LEATHER_CHESTPLATE);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems (NonNullList<ItemStack> itemList) {
        
        for (final ResourceLocation id : Costume.REGISTRY.getEntityIds()) {
            
            final ItemStack spawner = new ItemStack(Items.SPAWN_EGG);
            ItemMonsterPlacer.applyEntityIdToItemStack(spawner, id);
            itemList.add(spawner);
        }
        
        super.displayAllRelevantItems(itemList);
    }
    
    @Override
    public boolean hasSearchBar () {
        
        return true;
    }
}