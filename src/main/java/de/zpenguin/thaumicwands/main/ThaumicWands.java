package de.zpenguin.thaumicwands.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ThaumicWands.modID, name = ThaumicWands.modName, version = ThaumicWands.version, dependencies = ThaumicWands.dependencies)
public class ThaumicWands {

	public static final String modID = "thaumicwands";
	public static final String modName = "Thaumic Wands";
	public static final String version = "1.2.6";
	public static final String dependencies = "required-after:thaumcraft;";
	
	public static final Logger logger = LogManager.getLogger("Thaumic Wands");

	@Instance
	public static ThaumicWands instance;

	@SidedProxy(clientSide = "de.zpenguin.thaumicwands.client.ClientProxy", serverSide = "de.zpenguin.thaumicwands.main.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
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
