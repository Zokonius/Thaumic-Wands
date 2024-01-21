package com.interplay.thaumicwandsremastered.compat;

import com.interplay.thaumicwandsremastered.item.ItemBaseMeta;
import com.interplay.thaumicwandsremastered.main.TWR_Recipes;
import com.interplay.thaumicwandsremastered.main.ThaumicWandsRemastered;
import com.interplay.thaumicwandsremastered.util.ResearchHelper;
import com.interplay.thaumicwandsremastered.wand.WandCap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Level;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.*;

import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.THEORY;

public class PlanarArtificeCompat implements TWR_Compat.ICompat {

    public static Item itemWandCapPA = new ItemBaseMeta("item_wand_cap_pa", "alkimium", "bismuth");

    @Override
    public void init() {
        new WandCap("alkimium", 0.95F, new AspectList().add(Aspect.WATER,1).add(Aspect.EARTH,1).add(Aspect.ORDER,1), new ItemStack(itemWandCapPA, 1, 0), 15);
        new WandCap("bismuth", 0.85F, new AspectList().add(Aspect.ORDER,1).add(Aspect.ENTROPY,1), new ItemStack(itemWandCapPA, 1, 1), 20);
    }

    @Override
    public void initRecipes() {
        AspectList crystals;

        if(OreDictionary.doesOreNameExist("nuggetAlkimium") && OreDictionary.doesOreNameExist("nuggetBismuth")) {

            crystals = new AspectList().add(Aspect.WATER,4).add(Aspect.EARTH,4).add(Aspect.ORDER,4);
            TWR_Recipes.addShapedArcaneRecipe("CAP_ALKIMIUM.1", "CAP_ALKIMIUM", new ItemStack(itemWandCapPA), 25, crystals, "nnn","n n", 'n', "nuggetAlkimium");

            crystals = new AspectList().add(Aspect.AIR,6).add(Aspect.FIRE,6).add(Aspect.WATER,6).add(Aspect.EARTH,6).add(Aspect.ORDER,6).add(Aspect.ENTROPY,6);
            TWR_Recipes.addShapedArcaneRecipe("CAP_BISMUTH.1", "CAP_BISMUTH", new ItemStack(itemWandCapPA,1,1), 25, crystals, "nnn","n n", 'n', "nuggetBismuth");

        }

        else {
            ThaumicWandsRemastered.logger.log(Level.WARN,  "Somebody is using an outdated version of Planar Artifice. Skipping Compat for it.");
        }
    }

    @Override
    public void initResearch() {

        ResearchStage[] stages;
        String[] parents;

        if(OreDictionary.doesOreNameExist("nuggetAlkimium") && OreDictionary.doesOreNameExist("nuggetBismuth")) {

            // Alkimium Caps
            stages = new ResearchStage[] {
                    new ResearchHelper.RSB()
                            .setText("research.CAP_ALKIMIUM.stage.0")
                            .setRequiredCraft(TWR_Compat.getItem("planarartifice:alkimium_nugget", 0))
                            .setKnow(new Knowledge(THEORY, ResearchCategories.getResearchCategory("PLANARARTIFICE"), 1), new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMATURGY"), 1))
                            .build(),
                    new ResearchHelper.RSB()
                            .setText("research.CAP_ALKIMIUM.stage.1")
                            .setRecipes(TWR_Recipes.recipes.get("CAP_ALKIMIUM.1"))
                            .build()
            };
            parents = new String[] {"CAP_BRASS", "ALKIMIUM"};
            ResearchHelper.makeResearch("CAP_ALKIMIUM", "THAUMATURGY", -5, -1, new ItemStack(itemWandCapPA), stages, parents);

            // Bismuth Caps
            stages = new ResearchStage[] {
                    new ResearchHelper.RSB()
                            .setText("research.CAP_BISMUTH.stage.0")
                            .setRequiredCraft(TWR_Compat.getItem("planarartifice:bismuth_nugget", 0))
                            .setKnow(new Knowledge(THEORY, ResearchCategories.getResearchCategory("PLANARARTIFICE"), 2), new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMATURGY"), 2))
                            .build(),
                    new ResearchHelper.RSB()
                            .setText("research.CAP_BISMUTH.stage.1")
                            .setRecipes(TWR_Recipes.recipes.get("CAP_BISMUTH.1"))
                            .build()
            };
            parents = new String[] {"CAP_ALKIMIUM", "~CAP_THAUMIUM", "BISMUTH"};
            ResearchHelper.makeResearch("CAP_BISMUTH", "THAUMATURGY", -6, -4, new ItemStack(itemWandCapPA,1,1), stages, parents);


        }
    }

}
