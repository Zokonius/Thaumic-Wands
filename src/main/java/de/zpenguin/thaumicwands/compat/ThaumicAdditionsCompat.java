package de.zpenguin.thaumicwands.compat;

import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.THEORY;
import static thaumcraft.api.research.ResearchEntry.EnumResearchMeta.HIDDEN;

import de.zpenguin.thaumicwands.compat.TW_Compat.ICompat;
import de.zpenguin.thaumicwands.item.ItemBaseMeta;
import de.zpenguin.thaumicwands.item.TW_Items;
import de.zpenguin.thaumicwands.main.TW_Recipes;
import de.zpenguin.thaumicwands.util.research.ResearchHelper;
import de.zpenguin.thaumicwands.wand.WandCap;
import de.zpenguin.thaumicwands.wand.WandRod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.Knowledge;

public class ThaumicAdditionsCompat implements ICompat {

	public static Item itemWandCap = new ItemBaseMeta("item_wand_cap_ta", "mithrillium_inert", "mithrillium", "adaminite_inert", "adaminite", "mithminite_inert", "mithminite");
	public static Item itemWandRod = new ItemBaseMeta("item_wand_rod_ta", "adaminitewood");

	@Override
	public void init() {

        AspectList aspects;

        aspects = new AspectList().add(Aspect.AIR,1).add(Aspect.FIRE,1).add(Aspect.WATER,1).add(Aspect.EARTH,1).add(Aspect.ORDER,1).add(Aspect.ENTROPY,1);
		new WandCap("mithrillium", 0.75F, aspects, new ItemStack(itemWandCap,1,1), 30);

        aspects = new AspectList().add(Aspect.AIR,2).add(Aspect.FIRE,2).add(Aspect.WATER,2).add(Aspect.EARTH,2).add(Aspect.ORDER,2).add(Aspect.ENTROPY,2);
		new WandCap("adaminite", 0.7F, aspects, new ItemStack(itemWandCap,1,3), 35);

		aspects = new AspectList().add(Aspect.AIR,3).add(Aspect.FIRE,3).add(Aspect.WATER,3).add(Aspect.EARTH,3).add(Aspect.ORDER,3).add(Aspect.ENTROPY,3);
		new WandCap("mithminite", 0.6F, aspects, new ItemStack(itemWandCap,1,5), 40);

		new WandRod("adaminitewood", 3200, new ItemStack(itemWandRod,1,0), 40);

	}

	@Override
	public void initRecipes() {
		Ingredient primordialPearl = Ingredient.fromItem(ItemsTC.primordialPearl);

        AspectList crystals;
        AspectList aspects;

        crystals = new AspectList().add(Aspect.AIR,10).add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10);
		TW_Recipes.addShapedArcaneRecipe("CAP_MITHRILLIUM.1", "CAP_MITHRILLIUM@1",new ItemStack(itemWandCap,1, 0), 100, crystals, "nnn","n n", 'n', "nuggetMithrillium");

        crystals = new AspectList().add(Aspect.AIR,15).add(Aspect.FIRE,15).add(Aspect.WATER,15).add(Aspect.EARTH,15).add(Aspect.ORDER,15).add(Aspect.ENTROPY,15);
		TW_Recipes.addShapedArcaneRecipe("CAP_ADAMINITE.1", "CAP_ADAMINITE@1",new ItemStack(itemWandCap,1, 2), 150, crystals, "nnn","n n", 'n', "nuggetAdaminite");

        crystals = new AspectList().add(Aspect.AIR,20).add(Aspect.FIRE,20).add(Aspect.WATER,20).add(Aspect.EARTH,20).add(Aspect.ORDER,20).add(Aspect.ENTROPY,20);
		TW_Recipes.addShapedArcaneRecipe("CAP_MITHMINITE.1", "CAP_MITHMINITE@1", new ItemStack(itemWandCap,1, 4), 200, crystals, "nnn","n n", 'n', "nuggetMithminite");

		ItemStack ingotAdaminite = GameRegistry.makeItemStack("thaumadditions:adaminite_ingot", 0, 1, null);
		ItemStack clothAdaminite = GameRegistry.makeItemStack("thaumadditions:adaminite_fabric", 0, 1, null);

        aspects = new AspectList().add(Aspect.AIR,50).add(Aspect.FIRE,50).add(Aspect.WATER, 50).add(Aspect.EARTH, 50).add(Aspect.ORDER, 50).add(Aspect.ENTROPY, 50).add(Aspect.getAspect("draco"), 50).add(Aspect.MAGIC, 50);
		TW_Recipes.addInfusionRecipe("CAP_MITHRILLIUM.2", "CAP_MITHRILLIUM@1", new ItemStack(itemWandCap,1, 1),  5, new ItemStack(itemWandCap,1, 0), aspects, new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus));

		aspects = new AspectList().add(Aspect.AIR,50).add(Aspect.FIRE,50).add(Aspect.WATER, 50).add(Aspect.EARTH, 50).add(Aspect.ORDER, 50).add(Aspect.ENTROPY, 50).add(Aspect.getAspect("infernum"), 50).add(Aspect.MAGIC, 50);
		TW_Recipes.addInfusionRecipe("CAP_ADAMINITE.2", "CAP_ADAMINITE@1", new ItemStack(itemWandCap,1, 3), 8, new ItemStack(itemWandCap,1, 2), aspects, new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus));

		aspects = new AspectList().add(Aspect.AIR,50).add(Aspect.FIRE,50).add(Aspect.WATER, 50).add(Aspect.EARTH, 50).add(Aspect.ORDER, 50).add(Aspect.ENTROPY, 50).add(Aspect.getAspect("caeles"), 10).add(Aspect.MAGIC, 50);
		TW_Recipes.addInfusionRecipe("CAP_MITHMINITE.2", "CAP_MITHMINITE@1", new ItemStack(itemWandCap,1, 5), 6, new ItemStack(itemWandCap,1, 4), aspects, primordialPearl, new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus));

		aspects = new AspectList().add(Aspect.AIR,100).add(Aspect.FIRE,100).add(Aspect.WATER, 100).add(Aspect.EARTH, 100).add(Aspect.ORDER, 100).add(Aspect.ENTROPY, 100).add(Aspect.getAspect("draco"), 100).add(Aspect.getAspect("infernum"), 100);
		TW_Recipes.addInfusionRecipe("ROD_ADAMINITEWOOD.1", "ROD_ADAMINITEWOOD@1", new ItemStack(itemWandRod), 8, new ItemStack(TW_Items.itemWandRod, 1, 7), aspects, ingotAdaminite, ingotAdaminite, clothAdaminite, clothAdaminite, new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus));

	}

	@Override
	public void initResearch() {

        ResearchStage[] stages;
        String[] parents;

        // Mithrillium Caps
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
            .setText("research.CAP_MITHRILLIUM.stage.0")
            .setRequiredCraft(GameRegistry.makeItemStack("thaumadditions:mithrillium_nugget", 0, 1, null))
            .setKnow(new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMADDITIONS"), 2), new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMATURGY"), 2))
            .build(),
            new ResearchHelper.RSB()
            .setText("research.CAP_MITHRILLIUM.stage.1")
            .setRecipes(TW_Recipes.recipes.get("CAP_MITHRILLIUM.1"), TW_Recipes.recipes.get("CAP_MITHRILLIUM.2"))
            .build()
        };
        parents = new String[] {"~CAP_VOID", "TAR_MITHRILLIUM"};
        ResearchHelper.makeResearch("CAP_MITHRILLIUM", "THAUMATURGY", 2, -5, new ItemStack(itemWandCap,1,1), stages, parents, HIDDEN);


        // Adaminite Caps
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
            	.setText("research.CAP_ADAMINITE.stage.0")
                .setRequiredCraft(GameRegistry.makeItemStack("thaumadditions:adaminite_nugget", 0, 1, null))
                .setKnow(new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMADDITIONS"), 2), new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMATURGY"), 2))
            	.build(),
            new ResearchHelper.RSB()
            	.setText("research.CAP_ADAMINITE.stage.1")
                .setRecipes(TW_Recipes.recipes.get("CAP_ADAMINITE.1"), TW_Recipes.recipes.get("CAP_ADAMINITE.2"))
            	.build()
        };
        parents = new String[] {"CAP_MITHRILLIUM", "TAR_ADAMINITE"};
        ResearchHelper.makeResearch("CAP_ADAMINITE", "THAUMATURGY", 4, -5, new ItemStack(itemWandCap,1,3), stages, parents, HIDDEN);


        // Mithminite Caps
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
            	.setText("research.CAP_MITHMINITE.stage.0")
                .setRequiredCraft(GameRegistry.makeItemStack("thaumadditions:mithminite_nugget", 0, 1, null))
                .setKnow(new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMADDITIONS"), 2), new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMATURGY"), 2))
            	.build(),
            new ResearchHelper.RSB()
            	.setText("research.CAP_MITHMINITE.stage.1")
                .setRecipes(TW_Recipes.recipes.get("CAP_MITHMINITE.1"), TW_Recipes.recipes.get("CAP_MITHMINITE.2"))
            	.build()
        };
        parents = new String[] {"CAP_ADAMINITE", "TAR_MITHMINITE"};
        ResearchHelper.makeResearch("CAP_MITHMINITE", "THAUMATURGY", 6, -5, new ItemStack(itemWandCap,1,5), stages, parents, HIDDEN);


        // Adaminitewood Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
            .setText("research.ROD_ADAMINITEWOOD.stage.0")
            .setRequiredCraft(GameRegistry.makeItemStack("thaumadditions:adaminite_ingot", 0, 1, null), new ItemStack(TW_Items.itemWandRod, 1, 7))
            .setKnow(new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMADDITIONS"), 2), new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMATURGY"), 2))
            .build(),
            new ResearchHelper.RSB()
            .setText("research.ROD_ADAMINITEWOOD.stage.1")
            .setRecipes(TW_Recipes.recipes.get("ROD_ADAMINITEWOOD.1"))
            .build()
        };
        parents = new String[] {"~ROD_SILVERWOOD","TAR_ADAMINITE_FABRIC"};
        ResearchHelper.makeResearch("ROD_ADAMINITEWOOD", "THAUMATURGY", 4, -4, new ItemStack(itemWandRod,1,0), stages, parents, HIDDEN);

	}

}
