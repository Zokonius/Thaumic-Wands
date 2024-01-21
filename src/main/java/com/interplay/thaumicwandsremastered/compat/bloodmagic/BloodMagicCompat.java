package com.interplay.thaumicwandsremastered.compat.bloodmagic;

import WayofTime.bloodmagic.api.impl.BloodMagicAPI;
import WayofTime.bloodmagic.core.RegistrarBloodMagic;
import WayofTime.bloodmagic.core.RegistrarBloodMagicBlocks;
import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import WayofTime.bloodmagic.orb.BloodOrb;
import com.interplay.thaumicwandsremastered.compat.PolyCompat;
import com.interplay.thaumicwandsremastered.item.ItemBloodwell;
import com.interplay.thaumicwandsremastered.item.PolyItems;
import com.interplay.thaumicwandsremastered.item.TWR_Items;
import com.interplay.thaumicwandsremastered.main.PolyRecipes;
import com.interplay.thaumicwandsremastered.util.ResearchHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.*;

import static com.interplay.thaumicwandsremastered.main.PolyRecipes.recipes;
import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.OBSERVATION;
import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.THEORY;
import static thaumcraft.api.research.ResearchEntry.EnumResearchMeta.AUTOUNLOCK;
import static thaumcraft.api.research.ResearchEntry.EnumResearchMeta.ROUND;

public class BloodMagicCompat implements PolyCompat.IPolyCompat {

    public static Item itemBloodwell = new ItemBloodwell("item_bloodwell");

    private static ResearchStage[] stages;
    private static String[] parents;
    private static ResearchAddendum[] addenda;

    @Override
    public void preInit() {

    }

    @Override
    public void initRecipes() {

        Item slate = RegistrarBloodMagicItems.SLATE;
        Item component = RegistrarBloodMagicItems.COMPONENT;

        AspectList crystals;
        crystals = new AspectList().add(Aspect.AIR, 3).add(Aspect.FIRE, 3).add(Aspect.WATER, 3).add(Aspect.EARTH, 3).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3);
        PolyRecipes.addShapelessArcaneRecipe("BLOODWELL.1","BLOODWELL@1", new ItemStack(itemBloodwell), 25, crystals, getOrb(RegistrarBloodMagic.ORB_WEAK), new ItemStack(ItemsTC.phial), "feather");


        AspectList aspects;
        aspects = new AspectList().add(Aspect.LIFE,20).add(Aspect.MAGIC, 15).add(Aspect.WATER, 10);
        PolyRecipes.addInfusionRecipe("CAP_ALCHEMICAL.1","CAP_ALCHEMICAL@1", new ItemStack(PolyItems.itemWandCap, 1, 7), 3, new ItemStack(TWR_Items.itemWandCap, 1, 2), aspects, new ItemStack(component,1,0), new ItemStack(component,1,0), new ItemStack(component,1,0));

        aspects = new AspectList().add(Aspect.LIFE,50).add(Aspect.MAGIC, 20).add(Aspect.WATER, 25).add(Aspect.DARKNESS, 10).add(Aspect.ELDRITCH, 10);
        PolyRecipes.addInfusionRecipe("ROD_BLOOD.1","ROD_BLOOD@1", new ItemStack(PolyItems.itemWandRod, 1, 8), 5, getOrb(RegistrarBloodMagic.ORB_MASTER), aspects, new ItemStack(slate,1,2), new ItemStack(slate,1,2), new ItemStack(component,1,0), new ItemStack(component,1,1), new ItemStack(component,1,2), new ItemStack(component,1,3), new ItemStack(component,1,4), new ItemStack(component,1,5));

        BloodMagicAPI.INSTANCE.getRecipeRegistrar().addBloodAltar(Ingredient.fromStacks(new ItemStack(PolyItems.itemWandRod,1,8)), new ItemStack(PolyItems.itemWandRod, 1, 9) , 3, 20000, 15, 20);

    }

    @Override
    public void initResearch() {

        // Blood Magic
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.BLOODMAGIC.stage.0")
                        .build()
        };
        parents = new String[] {"FIRSTSTEPS"};
        ResearchHelper.makeResearch("BLOODMAGIC", "POLYMANCY", -3, 6, PolyCompat.getItem("guideapi:bloodmagic-guide", 0), stages, parents, ROUND, AUTOUNLOCK);

        // Bloodwell
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.BLOODWELL.stage.0")
                        .setKnow(new Knowledge(OBSERVATION, PolyCompat.getCategory("BASICS"), 1), new Knowledge(OBSERVATION, PolyCompat.getCategory("POLYMANCY"), 1))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.BLOODWELL.stage.1")
                        .setRecipes(recipes.get("BLOODWELL.1"))
                        .build()
        };
        parents = new String[] {"BLOODMAGIC"};
        ResearchHelper.makeResearch("BLOODWELL", "POLYMANCY", -4, 4, new ItemStack(itemBloodwell), stages, parents);

        // Blood Rod
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.ROD_BLOOD.stage.0")
                        .setRequiredCraft(PolyCompat.getItem("bloodmagic:slate", 2))
                        .setKnow(new Knowledge(THEORY, PolyCompat.getCategory("POLYMANCY"), 1), new Knowledge(THEORY, PolyCompat.getCategory("THAUMATURGY"), 1))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.ROD_BLOOD.stage.1")
                        .setRecipes(recipes.get("ROD_BLOOD.1"))
                        .build(),
        };
        parents = new String[] {"BLOODMAGIC"};
        ResearchHelper.makeResearch("ROD_BLOOD", "POLYMANCY", -5, 6, new ItemStack(PolyItems.itemWandRod,1,9), stages, parents);

        // Alchemical Cap
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.CAP_ALCHEMICAL.stage.0")
                        .setKnow(new Knowledge(THEORY, PolyCompat.getCategory("THAUMATURGY"), 1), new Knowledge(OBSERVATION, PolyCompat.getCategory("POLYMANCY"), 1))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.CAP_ALCHEMICAL.stage.1")
                        .setRecipes(recipes.get("CAP_ALCHEMICAL.1"))
                        .build(),
        };
        parents = new String[] {"ROD_BLOOD", "CAP_THAUMIUM"};
        ResearchHelper.makeResearch("CAP_ALCHEMICAL", "POLYMANCY", -6, 5, new ItemStack(PolyItems.itemWandCap, 1, 7), stages, parents);

        ResearchHelper.registerCards(CardBloodAltar.class, CardHellfireForge.class);

        ResearchHelper.makeAid(RegistrarBloodMagicBlocks.ALTAR, CardBloodAltar.class);
        ResearchHelper.makeAid(RegistrarBloodMagicBlocks.SOUL_FORGE, CardHellfireForge.class);


    }

    private static ItemStack getOrb(BloodOrb orb) {
        ItemStack bloodOrb = new ItemStack(RegistrarBloodMagicItems.BLOOD_ORB);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("orb", orb.getRegistryName().toString());
        bloodOrb.setTagCompound(nbt);
        return bloodOrb;
    }

}
