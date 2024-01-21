package com.interplay.thaumicwandsremastered.main;

import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = ThaumicWandsRemastered.MODID, name = ThaumicWandsRemastered.NAME, version = ThaumicWandsRemastered.VERSION, dependencies = ThaumicWandsRemastered.dependencies)
public class ThaumicWandsRemastered {

    public static final String MODID = "thaumicwandsremastered";
    public static final String NAME = "Thaumic Wands Remastered";
    public static final String VERSION = "1.0";
    public static final String dependencies = "after:thaumcraft;"+
                                              "after:botania;"+
                                              "after:bloodmagic";

    public static Logger logger = LogManager.getLogger("Thaumic Wands Remastered");

    @Instance
    public static ThaumicWandsRemastered instance;

    @SidedProxy(clientSide = "com.interplay.thaumicwandsremastered.client.ClientProxy", serverSide = "com.interplay.thaumicwandsremastered.main.CommonProxy")
    public static CommonProxy proxy;


    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        ConfigHandler.loadConfig(e.getSuggestedConfigurationFile());
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @EventHandler
    public void loadComplete(FMLLoadCompleteEvent e) {
        proxy.loadComplete(e);
    }
}
