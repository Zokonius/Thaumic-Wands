package de.zpenguin.thaumicwands.event;

import de.zpenguin.thaumicwands.entity.EntityVisOrb;
import de.zpenguin.thaumicwands.main.ThaumicWands;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;

@EventBusSubscriber(modid=ThaumicWands.modID)
public class TW_EventHandler {

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
