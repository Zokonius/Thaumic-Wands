package com.interplay.thaumicwandsremastered.entiy;

import com.interplay.thaumicwandsremastered.main.ThaumicWandsRemastered;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class TWR_Entities {
    public static void registerEntities(RegistryEvent.Register<EntityEntry> r) {
        EntityRegistry.registerModEntity(new ResourceLocation(ThaumicWandsRemastered.MODID, "VisOrb"), EntityVisOrb.class, "visOrb", 0, ThaumicWandsRemastered.instance, 120, 20, true);
    }
}
