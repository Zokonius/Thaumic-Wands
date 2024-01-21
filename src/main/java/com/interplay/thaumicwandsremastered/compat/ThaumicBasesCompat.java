package com.interplay.thaumicwandsremastered.compat;

import com.interplay.thaumicwandsremastered.item.ItemBaseMeta;
import com.interplay.thaumicwandsremastered.main.TWR_Recipes;
import com.interplay.thaumicwandsremastered.util.ResearchHelper;
import com.interplay.thaumicwandsremastered.wand.WandCap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.*;

import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.THEORY;

public class ThaumicBasesCompat implements TWR_Compat.ICompat {

    public static Item itemWandCapTB = new ItemBaseMeta("item_wand_cap_tb", "thauminite");

    @Override
    public void init() {
        new WandCap("thauminite", 0.85F, new AspectList().add(Aspect.ORDER,1), new ItemStack(itemWandCapTB), 20);
    }

    @Override
    public void initRecipes() {
        AspectList crystals;

        crystals = new AspectList().add(Aspect.AIR,6).add(Aspect.FIRE,6).add(Aspect.WATER,6).add(Aspect.EARTH,6).add(Aspect.ORDER,6).add(Aspect.ENTROPY,6);
        TWR_Recipes.addShapedArcaneRecipe("CAP_THAUMINITE.1", "CAP_THAUMINITE", new ItemStack(itemWandCapTB), 25, crystals, "nnn","nsn","sss", 'n', "nuggetThauminite", 's', ItemsTC.salisMundus);

    }

    @Override
    public void initResearch() {

        ResearchStage[] stages;
        String[] parents;

        // Thauminite Caps
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.CAP_THAUMINITE.stage.0")
                        .setRequiredCraft(GameRegistry.makeItemStack("thaumicbases:nuggetthauminite", 0, 1, null))
                        .setKnow(new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMICBASES"), 2), new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMATURGY"), 2))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.CAP_THAUMINITE.stage.1")
                        .setRecipes(TWR_Recipes.recipes.get("CAP_THAUMINITE.1"))
                        .build()
        };
        parents = new String[] {"CAP_THAUMIUM", "TB.ALCHEMY"};
        ResearchHelper.makeResearch("CAP_THAUMINITE", "THAUMATURGY", -1, -7, new ItemStack(itemWandCapTB), stages, parents);


    }

}
