package net.darkhax.costume;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemCostumeBag extends Item {
    
    public ItemCostumeBag() {
        
        this.maxStackSize = 1;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        
        tooltip.add(I18n.format("costume.tooltip.open"));
    }
    
    @Override
    public EnumRarity getRarity (ItemStack stack) {
        
        return EnumRarity.EPIC;
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        
        if (!worldIn.isRemote) {
            
            final CostumeType type = CostumeType.getRandom();
            
            for (Item item : type.getCostumeItems()) {
                
                ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(item));
            }
        }
        
        final ItemStack stack = playerIn.getHeldItem(handIn);      
        stack.shrink(1);
        
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
