package net.darkhax.costume;

import java.util.HashSet;
import java.util.Set;

import net.darkhax.bookshelf.lib.LoggingHelper;
import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.bookshelf.util.BiomeUtils;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Costume.MODID, name = Costume.NAME, version = "@VERSION@", dependencies = "required-after:bookshelf", certificateFingerprint = "@FINGERPRINT@")
public class Costume {
    
    public static final String MODID = "costume";
    public static final String NAME = "Costume";
    
    public static final LoggingHelper LOG = new LoggingHelper(NAME);
    public static final RegistryHelper REGISTRY = new RegistryHelper(MODID).setTab(new CreativeTabCostume()).enableAutoRegistration();
    
    @EventHandler
    public void onPreInit (FMLPreInitializationEvent event) {
        
        // Register and initialize all costume types
        for (final CostumeType costume : CostumeType.values()) {
            
            final ArmorMaterial material = EnumHelper.addArmorMaterial(costume.name(), MODID + ":" + costume.getName(), 1024, new int[] { 1, 1, 1, 1 }, 5, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 0f);
            
            final Item[] costumeItems = costume.getCostumeItems();
            costumeItems[0] = REGISTRY.registerItem(new ItemCostume(material, EntityEquipmentSlot.HEAD), "helmet_" + costume.getName());
            costumeItems[1] = REGISTRY.registerItem(new ItemCostume(material, EntityEquipmentSlot.CHEST), "chest_" + costume.getName());
            costumeItems[2] = REGISTRY.registerItem(new ItemCostume(material, EntityEquipmentSlot.LEGS), "legs_" + costume.getName());
            costumeItems[3] = REGISTRY.registerItem(new ItemCostume(material, EntityEquipmentSlot.FEET), "feet_" + costume.getName());
        }
        
        // Iterate over items after the fact and set their name to all be the same.
        for (final Item item : REGISTRY.getItems()) {
            
            if (item instanceof ItemArmor) {
                
                item.setTranslationKey("costume.costume");
            }
        }
        
        REGISTRY.registerMob(EntityLivingCostume.class, "living_costume", 0, 0x7b68ee, 0xb0e0e6);
    }
    
    @EventHandler
    @SideOnly(Side.CLIENT)
    public void onClientPreInit (FMLPreInitializationEvent event) {
        
        RenderingRegistry.registerEntityRenderingHandler(EntityLivingCostume.class, RenderLivingCostume::new);
    }
    
    @EventHandler
    public void onPostInit (FMLPostInitializationEvent event) {
        
        final Set<Biome> biomes = new HashSet<>();
        
        biomes.addAll(BiomeUtils.getBiomesForTypes(BiomeDictionary.Type.FOREST));
        biomes.addAll(BiomeUtils.getBiomesForTypes(BiomeDictionary.Type.MOUNTAIN));
        biomes.addAll(BiomeUtils.getBiomesForTypes(BiomeDictionary.Type.SPOOKY));
        biomes.addAll(BiomeUtils.getBiomesForTypes(BiomeDictionary.Type.MAGICAL));
        
        for (final Biome biome : biomes) {
            
            biome.getSpawnableList(EnumCreatureType.MONSTER).add(new SpawnListEntry(EntityLivingCostume.class, 5, 0, 0));
        }
    }
}
