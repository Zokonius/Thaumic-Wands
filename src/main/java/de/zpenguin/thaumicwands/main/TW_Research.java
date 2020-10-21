package de.zpenguin.thaumicwands.main;

import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.*;
import static thaumcraft.api.research.ResearchEntry.EnumResearchMeta.*;

import java.util.LinkedHashMap;

import de.zpenguin.thaumicwands.compat.TW_Compat;
import de.zpenguin.thaumicwands.item.TW_Items;
import de.zpenguin.thaumicwands.util.WandHelper;
import de.zpenguin.thaumicwands.util.research.ResearchHelper;
import de.zpenguin.thaumicwands.util.research.cards.CardConductance;
import de.zpenguin.thaumicwands.util.research.cards.CardFocussing;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.Knowledge;
import thaumcraft.common.config.ConfigResearch;
import thaumcraft.common.lib.CommandThaumcraft;

@EventBusSubscriber
public class TW_Research {

	public static ResearchCategory catThaumaturgy;
//	public static final ResourceLocation iconVisorb = new ResourceLocation(ThaumicWands.modID, "textures/research/r_visorb.png");
	public static final ResourceLocation iconThaumaturgy = new ResourceLocation(ThaumicWands.modID,"textures/research/icon_thaumaturgy.png");
	public static final ResourceLocation backThaumaturgy = new ResourceLocation(ThaumicWands.modID,"textures/research/tab_thaumaturgy.jpg");
	public static final ResourceLocation backOverlay = new ResourceLocation("thaumcraft","textures/gui/gui_research_back_over.png");
	public static final AspectList tabAspects = new AspectList().add(Aspect.MAGIC, 20)
																.add(Aspect.CRAFT, 15)
																.add(Aspect.AURA, 15)
																.add(Aspect.PLANT, 15)
																.add(Aspect.ENERGY, 10);




	@SubscribeEvent
	public static void commandEvent(CommandEvent ce) {
		if(ce.getCommand() instanceof CommandThaumcraft && ce.getParameters().length > 0 && ce.getParameters()[0].equalsIgnoreCase("reload")){
			new Thread(() -> {
				while(ResearchCategories.getResearchCategory("BASICS").research.containsKey("THAUMATURGY"))
				try {
						Thread.sleep(10L);
					}
				catch(InterruptedException e){
						e.printStackTrace();
					}

				init();
			}).start();
		}
	}


	public static void init() {
		catThaumaturgy =  ResearchCategories.registerCategory("THAUMATURGY", "FIRSTSTEPS", tabAspects, iconThaumaturgy, backThaumaturgy, backOverlay);

        createCards();

        ResearchStage[] stages;
        String[] parents;
        ResearchAddendum[] addenda;

        // Unlock Thaumaturgy
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
                .setText("research.UNLOCKTHAUMATURGY.stage.0")
                .setKnow(new Knowledge(OBSERVATION, catThaumaturgy, 1), new Knowledge(OBSERVATION, getCategory("BASICS"), 1))
                .build(),
            new ResearchHelper.RSB()
            	.setText("research.UNLOCKTHAUMATURGY.stage.1")
                .setRecipes(TW_Recipes.recipes.get("BASETHAUMATURGY.1"),TW_Recipes.recipes.get("BASETHAUMATURGY.2"))
                .setRequiredCraft(WandHelper.getWandWithParts("wood", "iron"))
                .build()
        };
        parents = new String[] {"FIRSTSTEPS"};
        ResearchHelper.makeResearch("UNLOCKTHAUMATURGY", "BASICS", -2, 1, iconThaumaturgy, stages, parents, ROUND, SPIKY);

        // Basic Thaumaturgy
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
        		.setText("research.BASETHAUMATURGY.stage.0")
                .setRecipes(TW_Recipes.recipes.get("BASETHAUMATURGY.1"),TW_Recipes.recipes.get("BASETHAUMATURGY.2"))
                .build()
        };

        addenda = new ResearchAddendum[] {
        		new ResearchHelper.RAB()
        		.setResearch("BASEAUROMANCY")
        		.setText("research.BASETHAUMATURGY.addendum.0")
        		.build()
        };

        parents = new String[] {"UNLOCKTHAUMATURGY"};
        ResearchHelper.makeThaumaturgyResearch("BASETHAUMATURGY", 0, 0, iconThaumaturgy, stages, parents, addenda, ROUND, HIDDEN);

        // Primal Charm
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
                .setText("research.PRIMALCHARM.stage.0")
                .setKnow(new Knowledge(OBSERVATION, catThaumaturgy, 1), new Knowledge(OBSERVATION, getCategory("BASICS"), 1))
                .build(),
            new ResearchHelper.RSB()
            	.setText("research.PRIMALCHARM.stage.1")
                .setRecipes(TW_Recipes.recipes.get("PRIMALCHARM.1"))
                .build()
        };
        parents = new String[] {"BASETHAUMATURGY", "UNLOCKAUROMANCY@2","METALLURGY@2"};
        ResearchHelper.makeThaumaturgyResearch("PRIMALCHARM", 0, -2, new ItemStack(TW_Items.itemPrimalCharm), stages, parents);


        // Copper Caps
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
    			.setText("research.CAP_COPPER.stage.0")
                .setKnow(new Knowledge(OBSERVATION, catThaumaturgy, 1))
                .build(),
            new ResearchHelper.RSB()
    			.setText("research.CAP_COPPER.stage.1")
                .setRecipes(TW_Recipes.recipes.get("CAP_COPPER.1"))
                .build()
        };
        parents = new String[] {"BASETHAUMATURGY"};
        ResearchHelper.makeThaumaturgyResearch("CAP_COPPER", -3, 0, new ItemStack(TW_Items.itemWandCap,1,1), stages, parents);

        // Brass Caps
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
				.setText("research.CAP_BRASS.stage.0")
                .setKnow(new Knowledge(THEORY, catThaumaturgy, 1),new Knowledge(OBSERVATION, catThaumaturgy, 1), new Knowledge(OBSERVATION, getCategory("ALCHEMY"), 1))
                .setConsumedItems(new ItemStack(ItemsTC.ingots,1,2))
                .build(),
            new ResearchHelper.RSB()
				.setText("research.CAP_BRASS.stage.1")
                .setRecipes(TW_Recipes.recipes.get("CAP_BRASS.1"))
                .build()
        };
        parents = new String[] {"BASETHAUMATURGY","METALLURGY@2"};
        ResearchHelper.makeThaumaturgyResearch("CAP_BRASS", -2, -2, new ItemStack(TW_Items.itemWandCap,1,2), stages, parents);

        // Silver Caps
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
            	.setText("research.CAP_SILVER.stage.0")
        		.setKnow(new Knowledge(OBSERVATION, catThaumaturgy, 2),new Knowledge(THEORY, catThaumaturgy, 1), new Knowledge(THEORY, getCategory("INFUSION"), 1))
                .build(),
            new ResearchHelper.RSB()
            	.setText("research.CAP_SILVER.stage.1")
                .setRecipes(TW_Recipes.recipes.get("CAP_SILVER.1"),TW_Recipes.recipes.get("CAP_SILVER.2"))
                .build()
        };
        parents = new String[] {"CAP_BRASS","INFUSION"};
        ResearchHelper.makeThaumaturgyResearch("CAP_SILVER", -4, -3, new ItemStack(TW_Items.itemWandCap,1,4), stages, parents);

        // Thaumium Caps
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
				.setText("research.CAP_THAUMIUM.stage.0")
        		.setKnow(new Knowledge(OBSERVATION, catThaumaturgy, 2),new Knowledge(THEORY, catThaumaturgy, 1), new Knowledge(THEORY, getCategory("INFUSION"), 1))
                .setConsumedItems(new ItemStack(ItemsTC.ingots,1,0))
                .build(),
            new ResearchHelper.RSB()
				.setText("research.CAP_THAUMIUM.stage.1")
                .setRecipes(TW_Recipes.recipes.get("CAP_THAUMIUM.1"),TW_Recipes.recipes.get("CAP_THAUMIUM.2"))
                .build()
        };
        parents = new String[] {"CAP_BRASS","METALLURGY@3","INFUSION"};
        ResearchHelper.makeThaumaturgyResearch("CAP_THAUMIUM", -2, -5, new ItemStack(TW_Items.itemWandCap,1,6), stages, parents);

        // Void Caps
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
				.setText("research.CAP_VOID.stage.0")
				.setKnow(new Knowledge(THEORY, catThaumaturgy, 2), new Knowledge(THEORY, getCategory("INFUSION"), 1), new Knowledge(THEORY, getCategory("ELDRITCH"), 1))
                .build(),
            new ResearchHelper.RSB()
			.setText("research.CAP_VOID.stage.1")
                .setRecipes(TW_Recipes.recipes.get("CAP_VOID.1"),TW_Recipes.recipes.get("CAP_VOID.2"))
                .setWarp(2)
                .build()
        };
        parents = new String[] {"CAP_THAUMIUM","BASEELDRITCH@2","INFUSION"};
        ResearchHelper.makeThaumaturgyResearch("CAP_VOID", -4, -6, new ItemStack(TW_Items.itemWandCap,1,8), stages, parents);

        // Greatwood Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
				.setText("research.ROD_GREATWOOD.stage.0")
                .setKnow(new Knowledge(THEORY, catThaumaturgy, 1), new Knowledge(OBSERVATION, catThaumaturgy, 1), new Knowledge(OBSERVATION, getCategory("BASICS"), 1))
                .build(),
            new ResearchHelper.RSB()
				.setText("research.ROD_GREATWOOD.stage.1")
                .setRecipes(TW_Recipes.recipes.get("ROD_GREATWOOD.1"))
                .build()
        };
        parents = new String[] {"BASETHAUMATURGY","!PLANTWOOD"};
        ResearchHelper.makeThaumaturgyResearch("ROD_GREATWOOD", 1, 2, new ItemStack(TW_Items.itemWandRod,1,0), stages, parents);

        // Reed Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
				.setText("research.ROD_REED.stage.0")
                .setKnow(new Knowledge(THEORY, catThaumaturgy, 1), new Knowledge(THEORY, getCategory("INFUSION"), 1), new Knowledge(OBSERVATION, catThaumaturgy, 2))
                .setConsumedItems(new ItemStack(Items.REEDS))
                .build(),
            new ResearchHelper.RSB()
				.setText("research.ROD_REED.stage.1")
                .setRecipes(TW_Recipes.recipes.get("ROD_REED.1"))
                .build()
        };
        parents = new String[] {"ROD_GREATWOOD","INFUSION"};
        ResearchHelper.makeThaumaturgyResearch("ROD_REED", 3, 1, new ItemStack(TW_Items.itemWandRod,1,1), stages, parents);


        // Blaze Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
				.setText("research.ROD_BLAZE.stage.0")
                .setKnow(new Knowledge(THEORY, catThaumaturgy, 1), new Knowledge(THEORY, getCategory("INFUSION"), 1), new Knowledge(OBSERVATION, catThaumaturgy, 2))
                .setConsumedItems(new ItemStack(Items.BLAZE_ROD))
                .build(),
            new ResearchHelper.RSB()
				.setText("research.ROD_BLAZE.stage.1")
                .setRecipes(TW_Recipes.recipes.get("ROD_BLAZE.1"))
                .build()
        };
        parents = new String[] {"ROD_GREATWOOD","INFUSION"};
        ResearchHelper.makeThaumaturgyResearch("ROD_BLAZE", 4, 1, new ItemStack(TW_Items.itemWandRod,1,2), stages, parents);


        // Ice Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
				.setText("research.ROD_ICE.stage.0")
                .setKnow(new Knowledge(THEORY, catThaumaturgy, 1), new Knowledge(THEORY, getCategory("INFUSION"), 1), new Knowledge(OBSERVATION, catThaumaturgy, 2))
                .setConsumedItems(new ItemStack(Blocks.ICE))
                .build(),
            new ResearchHelper.RSB()
            	.setText("research.ROD_ICE.stage.1")
                .setRecipes(TW_Recipes.recipes.get("ROD_ICE.1"))
                .build()
        };
        parents = new String[] {"ROD_GREATWOOD","INFUSION"};
        ResearchHelper.makeThaumaturgyResearch("ROD_ICE", 5, 1, new ItemStack(TW_Items.itemWandRod,1,3), stages, parents);

        // Obsidian Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
				.setText("research.ROD_OBSIDIAN.stage.0")
                .setKnow(new Knowledge(THEORY, catThaumaturgy, 1), new Knowledge(THEORY, getCategory("INFUSION"), 1), new Knowledge(OBSERVATION, catThaumaturgy, 2))
                .setConsumedItems(new ItemStack(Blocks.OBSIDIAN))
                .build(),
            new ResearchHelper.RSB()
				.setText("research.ROD_OBSIDIAN.stage.1")
                .setRecipes(TW_Recipes.recipes.get("ROD_OBSIDIAN.1"))
                .build()
        };
        parents = new String[] {"ROD_GREATWOOD","INFUSION"};
        ResearchHelper.makeThaumaturgyResearch("ROD_OBSIDIAN", 3, 3, new ItemStack(TW_Items.itemWandRod,1,4), stages, parents);

        // Quartz Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
            	.setText("research.ROD_QUARTZ.stage.0")
                .setKnow(new Knowledge(THEORY, catThaumaturgy, 1), new Knowledge(THEORY, getCategory("INFUSION"), 1), new Knowledge(OBSERVATION, catThaumaturgy, 2))
                .setConsumedItems(new ItemStack(Blocks.QUARTZ_BLOCK))
                .build(),
            new ResearchHelper.RSB()
				.setText("research.ROD_QUARTZ.stage.1")
                .setRecipes(TW_Recipes.recipes.get("ROD_QUARTZ.1"))
                .build()
        };
        parents = new String[] {"ROD_GREATWOOD","INFUSION"};
        ResearchHelper.makeThaumaturgyResearch("ROD_QUARTZ", 4, 3, new ItemStack(TW_Items.itemWandRod,1,5), stages, parents);

        // Bone Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
            	.setText("research.ROD_BONE.stage.0")
                .setKnow(new Knowledge(THEORY, catThaumaturgy, 1), new Knowledge(THEORY, getCategory("INFUSION"), 1), new Knowledge(OBSERVATION, catThaumaturgy, 2))
                .setConsumedItems(new ItemStack(Items.BONE))
                .build(),
            new ResearchHelper.RSB()
            	.setText("research.ROD_BONE.stage.1")
                .setRecipes(TW_Recipes.recipes.get("ROD_BONE.1"))
                .setWarp(2)
                .build()
        };
        parents = new String[] {"ROD_GREATWOOD","INFUSION"};
        ResearchHelper.makeThaumaturgyResearch("ROD_BONE", 5, 3, new ItemStack(TW_Items.itemWandRod,1,6), stages, parents);

        // Silverwood Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
				.setText("research.ROD_SILVERWOOD.stage.0")
                .setKnow(new Knowledge(THEORY, catThaumaturgy, 2), new Knowledge(THEORY, getCategory("INFUSION"), 1), new Knowledge(OBSERVATION, catThaumaturgy, 2))
                .build(),
            new ResearchHelper.RSB()
				.setText("research.ROD_SILVERWOOD.stage.1")
                .setRecipes(TW_Recipes.recipes.get("ROD_SILVERWOOD.1"))
                .build()
        };
        parents = new String[] {"ROD_GREATWOOD","INFUSION"};
        ResearchHelper.makeThaumaturgyResearch("ROD_SILVERWOOD", 7, 5, new ItemStack(TW_Items.itemWandRod,1,7), stages, parents);

        TW_Compat.initResearch();

	}

	private static void createCards() {
		String[] nuggets = new String[]{"Copper","Tin","Silver","Lead"};
		for(int i=0;i!=nuggets.length;i++)
			if(OreDictionary.doesOreNameExist("ingot"+nuggets[i]))
				CardConductance.nuggets.add(new ItemStack(ItemsTC.nuggets,1,i+1));

		ResearchHelper.registerCards(CardFocussing.class, CardConductance.class);
	}

	public static void postInit() {
		ResearchEntry entry = ResearchCategories.getResearch("FIRSTSTEPS");
		ResearchStage[] stages = entry.getStages();
		stages[2].setRecipes(new ResourceLocation[]{TW_Recipes.recipes.get("FIRSTSTEPS.1")});
		entry.setStages(stages);

		ConfigResearch.TCCategories = new String[] {"BASICS", "THAUMATURGY", "ALCHEMY", "AUROMANCY", "ARTIFICE", "INFUSION", "GOLEMANCY", "ELDRITCH"};
		LinkedHashMap<String, ResearchCategory> map = new LinkedHashMap<>();
		map.put("BASICS", ResearchCategories.researchCategories.get("BASICS"));
		map.put("THAUMATURGY", ResearchCategories.researchCategories.get("THAUMATURGY"));
		for(String s:ResearchCategories.researchCategories.keySet()) {
			if(!map.containsKey(s))
				map.put(s, ResearchCategories.researchCategories.get(s));
		}

		ResearchCategories.researchCategories = map;
	}




    public static ResearchCategory getCategory(String cat) {
        return ResearchCategories.getResearchCategory(cat);
    }

}
