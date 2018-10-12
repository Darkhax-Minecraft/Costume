package net.darkhax.costume;

import javax.annotation.Nullable;

import net.darkhax.bookshelf.lib.Constants;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityLivingCostume extends EntityMob {
    
    public EntityLivingCostume(World worldIn) {
        
        super(worldIn);
        this.inventoryArmorDropChances = new float[] { 0f, 0f, 0f, 0f };
        this.experienceValue = 5;
    }
    
    @Override
    protected void initEntityAI () {
        
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }
    
    @Override
    protected void applyEntityAttributes () {
        
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23d);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4D);
    }
    
    @Override
    public IEntityLivingData onInitialSpawn (DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        
        final CostumeType costume = CostumeType.values()[Constants.RANDOM.nextInt(CostumeType.values().length)];
        
        final Item[] items = costume.getCostumeItems();
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(items[0]));
        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(items[1]));
        this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(items[2]));
        this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(items[3]));
        return livingdata;
    }
    
    @Override
    public ResourceLocation getLootTable () {
        
        return null; // EerieEntities.LOOT_CURSED_ARMOR;
    }
}