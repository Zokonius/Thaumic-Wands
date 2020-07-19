package de.zpenguin.thaumicwands.compat;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class TW_Compat {

	public static ArrayList<ICompat> compats = Lists.newArrayList();

	public static void preInit(FMLPreInitializationEvent e) {
		if(Loader.isModLoaded("thaumadditions"))
			compats.add(new ThaumicAdditionsCompat());
		if(Loader.isModLoaded("thaumicbases"))
			compats.add(new ThaumicBasesCompat());
	}

	public static void initRecipes() {
		for(ICompat c: compats)
			c.initRecipes();
	}

	public static void initResearch() {
		for(ICompat c: compats)
			c.initResearch();
	}

	public static interface ICompat {

		public void init();

		public void initRecipes();

		public void initResearch();

	}

}
