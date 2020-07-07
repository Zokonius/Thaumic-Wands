package de.zpenguin.thaumicwands.main;

import de.zpenguin.thaumicwands.tile.TW_Tiles;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
    	TW_Tiles.registerTiles();
	}

	public void init(FMLInitializationEvent e) {
		TW_Research.init();
    	NetworkRegistry.INSTANCE.registerGuiHandler(ThaumicWands.instance, new TW_GuiHandler());
	}

	public void postInit(FMLPostInitializationEvent e) {
		TW_Research.postInit();
	}

}
