package de.zpenguin.thaumicwands.compat;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TW_Compat {

	public static ArrayList<ICompat> compats = Lists.newArrayList();

	public static void preInit(FMLPreInitializationEvent e) {
		if(Loader.isModLoaded("thaumadditions"))
			compats.add(new ThaumicAdditionsCompat());
		if(Loader.isModLoaded("thaumicbases"))
			compats.add(new ThaumicBasesCompat());
		if(Loader.isModLoaded("planarartifice"))
			compats.add(new PlanarArtificeCompat());
	}

	public static void init() {
		for(ICompat c: compats)
			c.init();
	}

	public static void initRecipes() {
		for(ICompat c: compats)
			c.initRecipes();
	}

	public static void initResearch() {
		for(ICompat c: compats)
			c.initResearch();
	}

	public static void loadComplete() {
		for(ICompat c:compats)
			c.loadComplete();
	}


	public static interface ICompat {

		public void init();

		public void initRecipes();

		public void initResearch();

		public default void loadComplete() {}

	}

    public static ItemStack getItem(String name, int meta) {
    	return GameRegistry.makeItemStack(name, meta, 1, null);
    }


}
