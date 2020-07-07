package de.zpenguin.thaumicwands.tile;

import de.zpenguin.thaumicwands.main.ThaumicWands;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TW_Tiles {

	public static void registerTiles() {
		GameRegistry.registerTileEntity(TileArcaneWorkbenchNew.class, new ResourceLocation(ThaumicWands.modID, "tileArcaneWorkbench"));
	}

}
