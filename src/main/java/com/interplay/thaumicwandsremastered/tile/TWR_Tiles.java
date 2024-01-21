package com.interplay.thaumicwandsremastered.tile;

import com.interplay.thaumicwandsremastered.main.ThaumicWandsRemastered;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TWR_Tiles {
    public static void registerTiles() {
        GameRegistry.registerTileEntity(TileArcaneWorkbenchNew.class, new ResourceLocation(ThaumicWandsRemastered.MODID, "tileArcaneWorkbench"));
    }
}
