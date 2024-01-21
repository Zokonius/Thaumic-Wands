package com.interplay.thaumicwandsremastered.compat;

import com.interplay.thaumicwandsremastered.compat.bloodmagic.BloodMagicCompat;
import com.interplay.thaumicwandsremastered.compat.botania.BotaniaCompat;
import com.interplay.thaumicwandsremastered.main.ConfigHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;

import java.util.ArrayList;

public class PolyCompat {
    public static boolean botania = Loader.isModLoaded("botania") && ConfigHandler.bot;
    public static boolean bloodMagic = Loader.isModLoaded("bloodmagic") && ConfigHandler.blood;
    public static boolean astralSorcery = Loader.isModLoaded("astralsorcery") && ConfigHandler.astral;
    public static boolean projecte = Loader.isModLoaded("projecte") && ConfigHandler.projecte;

    private static ArrayList<IPolyCompat> compat = new ArrayList<>();

    public static void preInit() {
        if(botania)
            compat.add(new BotaniaCompat());
        if(bloodMagic)
            compat.add(new BloodMagicCompat());

        for(IPolyCompat c: compat)
            c.preInit();
    }

    public static void initRecipes() {
        for(IPolyCompat c: compat)
            c.initRecipes();
    }

    public static void initResearch() {
        for(IPolyCompat c: compat)
            c.initResearch();
    }


    public static ItemStack getItem(String name, int meta) {
        return getItem(name, 1, meta);
    }

    public static ItemStack getItem(String name, int amount, int meta) {
        return GameRegistry.makeItemStack(name, meta, amount, null);
    }

    public static ResearchCategory getCategory(String cat) {
        return ResearchCategories.getResearchCategory(cat);
    }

    public interface IPolyCompat {

        void preInit();

        void initRecipes();

        void initResearch();

    }
}
