package de.zpenguin.thaumicwands.compat;

import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.THEORY;

import de.zpenguin.thaumicwands.compat.TW_Compat.ICompat;
import de.zpenguin.thaumicwands.item.ItemBaseMeta;
import de.zpenguin.thaumicwands.main.TW_Recipes;
import de.zpenguin.thaumicwands.util.research.ResearchHelper;
import de.zpenguin.thaumicwands.wand.WandCap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.Knowledge;

public class PlanarArtificeCompat implements ICompat {

	public static Item itemWandCapPA = new ItemBaseMeta("item_wand_cap_pa", "alkimium");

	@Override
	public void init() {
		new WandCap("alkimium", 0.95F,new AspectList().add(Aspect.WATER,1).add(Aspect.EARTH,1).add(Aspect.ORDER,1), new ItemStack(itemWandCapPA, 1, 0), 15);
		new WandCap("bismuth", 0.85F, new ItemStack(itemWandCapPA, 1, 1), 20);
	}

	@Override
	public void initRecipes() {
		OreDictionary.registerOre("nuggetAlkimium", TW_Compat.getItem("planarartifice:alkimium_nugget", 0));

        AspectList crystals;

        crystals = new AspectList().add(Aspect.WATER,4).add(Aspect.EARTH,4).add(Aspect.ORDER,4);
		TW_Recipes.addShapedArcaneRecipe("CAP_ALKIMIUM.1", "CAP_ALKIMIUM", new ItemStack(itemWandCapPA), 25, crystals, "nnn","n n", 'n', "nuggetAlkimium");

//      crystals = new AspectList().add(Aspect.AIR,6).add(Aspect.FIRE,6).add(Aspect.WATER,6).add(Aspect.EARTH,6).add(Aspect.ORDER,6).add(Aspect.ENTROPY,6);
//		TW_Recipes.addShapedArcaneRecipe("CAP_BISMUTH.1", "CAP_BISMUTH", new ItemStack(itemWandCapPA), 25, crystals, "nnn","n n", 'n', "nuggetBismuth");
	}

	@Override
	public void initResearch() {

        ResearchStage[] stages;
        String[] parents;

        // Alkimium Caps
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
            .setText("research.CAP_ALKIMIUM.stage.0")
            .setRequiredCraft(TW_Compat.getItem("planarartifice:alkimium_nugget", 0))
            .setKnow(new Knowledge(THEORY, ResearchCategories.getResearchCategory("PLANARARTIFICE"), 1), new Knowledge(THEORY, ResearchCategories.getResearchCategory("THAUMATURGY"), 1))
            .build(),
            new ResearchHelper.RSB()
            .setText("research.CAP_ALKIMIUM.stage.1")
            .setRecipes(TW_Recipes.recipes.get("CAP_ALKIMIUM.1"))
            .build()
        };
        parents = new String[] {"CAP_BRASS", "ALKIMIUM"};
        ResearchHelper.makeResearch("CAP_ALKIMIUM", "THAUMATURGY", "Alkimium Wand Caps", -5, -1, new ItemStack(itemWandCapPA), stages, parents);


	}

}
