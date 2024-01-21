package com.interplay.thaumicwandsremastered.util;

import com.interplay.thaumicwandsremastered.compat.PolyCompat;
import com.interplay.thaumicwandsremastered.item.PolyItems;
import com.interplay.thaumicwandsremastered.main.ThaumicWandsRemastered;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.*;
import thaumcraft.common.lib.CommandThaumcraft;

import static com.interplay.thaumicwandsremastered.main.PolyRecipes.recipes;
import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.OBSERVATION;
import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.THEORY;
import static thaumcraft.api.research.ResearchEntry.EnumResearchMeta.*;

public class PolyResearch {
    public static ResearchCategory catPolymancy;
    public static final ResourceLocation iconPolymancy = new ResourceLocation(ThaumicWandsRemastered.MODID,"textures/research/icon_polymancy.png");
    public static final ResourceLocation backPolymancy = new ResourceLocation(ThaumicWandsRemastered.MODID,"textures/research/tab_polymancy.jpg");
    public static final ResourceLocation backOverlay = new ResourceLocation(ThaumicWandsRemastered.MODID,"textures/research/overlay_polymancy.png");
    public static final AspectList tabAspects = new AspectList().add(Aspect.MAGIC, 20)
            .add(Aspect.CRAFT, 15)
            .add(Aspect.AURA, 15)
            .add(Aspect.PLANT, 15)
            .add(Aspect.ENERGY, 10);

    private static ResearchStage[] stages;
    private static String[] parents;
    private static ResearchAddendum[] addenda;

    public static void init() {
        catPolymancy = ResearchCategories.registerCategory("POLYMANCY", "FIRSTSTEPS", tabAspects, iconPolymancy, backPolymancy, backOverlay);

        // Polymancy
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.POLYMANCY.stage.0")
                        .build()
        };
        parents = new String[] {"FIRSTSTEPS"};
        ResearchHelper.makeResearch("POLYMANCY", "POLYMANCY", 0, 0, iconPolymancy, stages, parents, ROUND, AUTOUNLOCK);

        // Infernal Rod
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.ROD_INFERNAL.stage.0")
                        .setConsumedItems(new ItemStack(Items.BLAZE_ROD))
                        .setKnow(new ResearchStage.Knowledge(THEORY, getCategory("THAUMATURGY"), 1),new Knowledge(OBSERVATION,getCategory("AUROMANCY"), 1), new Knowledge(OBSERVATION, catPolymancy, 1))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.ROD_INFERNAL.stage.1")
                        .setRecipes(recipes.get("ROD_INFERNAL.1"))
                        .build()
        };
        parents = new String[] {"POLYMANCY", "ROD_SILVERWOOD"};
        ResearchHelper.makeResearch("ROD_INFERNAL", "POLYMANCY", -1, -2, new ItemStack(PolyItems.itemWandRod), stages, parents);

        // Chorus Rod
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.ROD_CHORUS.stage.0")
                        .setConsumedItems(new ItemStack(Items.CHORUS_FRUIT_POPPED))
                        .setKnow(new Knowledge(THEORY, getCategory("THAUMATURGY"), 1),new Knowledge(OBSERVATION,getCategory("AUROMANCY"), 1), new Knowledge(OBSERVATION, catPolymancy, 1))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.ROD_CHORUS.stage.1")
                        .setRecipes(recipes.get("ROD_CHORUS.1"))
                        .build()
        };
        parents = new String[] {"POLYMANCY", "ROD_SILVERWOOD"};
        ResearchHelper.makeResearch("ROD_CHORUS", "POLYMANCY", 1, -2, new ItemStack(PolyItems.itemWandRod,1, 1), stages, parents);

        // Primewell
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.PRIMEWELL.stage.0")
                        .setKnow(new Knowledge(THEORY, getCategory("BASICS"), 1))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.PRIMEWELL.stage.1")
                        .setRecipes(recipes.get("PRIMEWELL.1"))
                        .build()
        };
        parents = new String[] {"~POLYMANCY", "PRIMPEARL"};
        ResearchHelper.makeResearch("PRIMEWELL", "POLYMANCY", 0, 3, new ItemStack(PolyItems.itemPrimewell), stages, parents, HIDDEN);

        PolyCompat.initResearch();

    }

    public static ResearchCategory getCategory(String cat) {
        return ResearchCategories.getResearchCategory(cat);
    }

    public static final Logger LOGGER = LogManager.getLogger(ReflectionHelper.class);
    @SubscribeEvent
    public static void commandEvent(CommandEvent ce) {
        if(ce.getCommand() instanceof CommandThaumcraft && ce.getParameters().length > 0 && ce.getParameters()[0].equalsIgnoreCase("reload")) {
            new Thread(() -> {
                while(ResearchCategories.getResearchCategory("BASICS").research.containsKey("THAUMATURGY"))
                    try {
                        Thread.sleep(10L);
                    }
                    catch(InterruptedException ex){
                        LOGGER.error("An error occurred when trying to get the basic research category", ex);
                    }

                init();
            }).start();
        }
    }
}
