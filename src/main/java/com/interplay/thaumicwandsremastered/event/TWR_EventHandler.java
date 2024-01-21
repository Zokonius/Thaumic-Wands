package com.interplay.thaumicwandsremastered.event;

import com.interplay.thaumicwandsremastered.entiy.EntityVisOrb;
import com.interplay.thaumicwandsremastered.main.ThaumicWandsRemastered;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;

@Mod.EventBusSubscriber(modid = ThaumicWandsRemastered.MODID)
public class TWR_EventHandler {

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent e) {
        if(!e.getEntityLiving().world.isRemote) {
            AspectList aspects = AspectHelper.getEntityAspects(e.getEntityLiving());
            if(aspects !=null && aspects.visSize() > 0 && e.getEntityLiving().getEntityWorld().rand.nextBoolean()) {
                EntityVisOrb orb = new EntityVisOrb(e.getEntityLiving().getEntityWorld(), e.getEntityLiving().posX, e.getEntityLiving().posY, e.getEntityLiving().posZ, 1 + e.getEntityLiving().world.rand.nextInt(1 + Math.floorDiv(aspects.visSize(), 20)));
                e.getEntityLiving().getEntityWorld().spawnEntity(orb);
            }

        }
    }

}
