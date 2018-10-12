package net.darkhax.costume;

import java.util.List;

import javax.annotation.Nullable;

import net.darkhax.bookshelf.item.ICustomModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCostume extends ItemArmor implements ICustomModel {
    
    public ItemCostume(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) {
        
        super(materialIn, 0, equipmentSlotIn);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        
        tooltip.add(I18n.format("costume." + this.getArmorMaterial().name().toLowerCase() + ".name"));
    }
    
    @Override
    public EnumRarity getRarity (ItemStack stack) {
        
        return EnumRarity.RARE;
    }
    
    private static final ResourceLocation modelChest = new ResourceLocation("minecraft", "leather_chestplate");
    private static final ResourceLocation modelFeet = new ResourceLocation("minecraft", "leather_boots");
    private static final ResourceLocation modelHead = new ResourceLocation("minecraft", "leather_helmet");
    private static final ResourceLocation modelLegs = new ResourceLocation("minecraft", "leather_leggings");
    
    public ResourceLocation getModelFor (EntityEquipmentSlot slot) {
        
        switch (slot) {
            case CHEST:
                return modelChest;
            case FEET:
                return modelFeet;
            case HEAD:
                return modelHead;
            case LEGS:
                return modelLegs;
            default:
                return modelChest;
        }
    }
    
    @Override
    public void registerMeshModels () {
        
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getModelFor(this.armorType), "inventory"));
    }
}