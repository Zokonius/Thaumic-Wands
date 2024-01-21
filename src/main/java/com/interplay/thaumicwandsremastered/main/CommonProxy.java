package com.interplay.thaumicwandsremastered.main;

import com.interplay.thaumicwandsremastered.compat.PolyCompat;
import com.interplay.thaumicwandsremastered.compat.TWR_Compat;
import com.interplay.thaumicwandsremastered.tile.TWR_Tiles;
import com.interplay.thaumicwandsremastered.util.PolyResearch;
import com.interplay.thaumicwandsremastered.wand.TWR_Wands;
import com.interplay.thaumicwandsremastered.wand.polymancywands.PolyWands;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        TWR_Tiles.registerTiles();
        TWR_Compat.preInit(e);
        PolyWands.registerWandParts();
        PolyCompat.preInit();
    }

    public void init(FMLInitializationEvent e) {
        TWR_Compat.init();
        TWR_Research.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(ThaumicWandsRemastered.instance, new TWR_GuiHandler());
        TWR_Wands.registerWandParts();
        PolyResearch.init();
    }

    public void postInit(FMLPostInitializationEvent e) {
        TWR_Research.postInit();
    }

    public void loadComplete(FMLLoadCompleteEvent e) {

    }
}
