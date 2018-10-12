package net.darkhax.costume;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

public class RenderLivingCostume extends RenderLiving<EntityLivingCostume> {
    
    public RenderLivingCostume(RenderManager rendermanagerIn) {
        
        super(rendermanagerIn, new ModelBiped(), 0.45f);
        this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerHeldItem(this));
    }
    
    @Override
    protected ResourceLocation getEntityTexture (EntityLivingCostume entity) {
        
        return null;
    }
}