package de.zpenguin.thaumicwands.entity;

import de.zpenguin.thaumicwands.main.ThaumicWands;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class TW_Entities {

	public static void registerEntities(Register<EntityEntry> r) {
		EntityRegistry.registerModEntity(new ResourceLocation(ThaumicWands.modID, "VisOrb"), EntityVisOrb.class, "visOrb", 0, ThaumicWands.instance, 120, 20, true);
	}

}
