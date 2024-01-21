package com.interplay.thaumicwandsremastered.compat;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class TWR_Compat {

    public static ArrayList<TWR_Compat.ICompat> compats = Lists.newArrayList();

    public static void preInit(FMLPreInitializationEvent e) {
            if (Loader.isModLoaded("thaumadditions"))
                compats.add(new ThaumicAdditionsCompat());
            if (Loader.isModLoaded("thaumicbases"))
                compats.add(new ThaumicBasesCompat());
            if (Loader.isModLoaded("planarartifice"))
                compats.add(new PlanarArtificeCompat());
            if (Loader.isModLoaded("crafttweaker"))
                compats.add(new CraftTweakerCompat());
            if (Loader.isModLoaded("rusticthaumaturgy"))
                compats.add(new RusticThaumaturgyCompat());

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


    public interface ICompat {

        void init();

        void initRecipes();

        void initResearch();

        default void loadComplete() {}

    }

    public static ItemStack getItem(String name, int meta) {
        return GameRegistry.makeItemStack(name, meta, 1, null);

    }
}
