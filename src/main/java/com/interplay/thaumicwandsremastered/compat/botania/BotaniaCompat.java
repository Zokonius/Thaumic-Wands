package com.interplay.thaumicwandsremastered.compat.botania;

import com.interplay.thaumicwandsremastered.compat.PolyCompat;
import com.interplay.thaumicwandsremastered.item.PolyItems;
import com.interplay.thaumicwandsremastered.main.PolyRecipes;
import com.interplay.thaumicwandsremastered.util.ResearchHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.*;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

import static com.interplay.thaumicwandsremastered.main.PolyRecipes.recipes;
import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.OBSERVATION;
import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.THEORY;
import static thaumcraft.api.research.ResearchEntry.EnumResearchMeta.AUTOUNLOCK;
import static thaumcraft.api.research.ResearchEntry.EnumResearchMeta.ROUND;

public class BotaniaCompat implements PolyCompat.IPolyCompat {

    private static ResearchStage[] stages;
    private static String[] parents;
    private static ResearchAddendum[] addenda;

    @Override
    public void preInit() {

    }

    @Override
    public void initRecipes() {

        Item rune = ModItems.rune;
        Item manaResource = ModItems.manaResource;

        PolyRecipes.addShapedOreRecipe("CAP_TERRASTEEL.1", new ItemStack(PolyItems.itemWandCap, 1, 4), "NNN", "N N", 'N', "nuggetTerrasteel");

        AspectList crystals;
        crystals = new AspectList().add(Aspect.AIR, 5).add(Aspect.FIRE, 5).add(Aspect.WATER, 5).add(Aspect.EARTH, 5).add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 5);
        PolyRecipes.addShapedArcaneRecipe("CAP_MANASTEEL.1","CAP_MANASTEEL@1", new ItemStack(PolyItems.itemWandCap, 1, 0), 10, crystals, "NNN","N N",'N',"nuggetManasteel");

        crystals = new AspectList().add(Aspect.AIR, 7).add(Aspect.FIRE, 7).add(Aspect.WATER, 7).add(Aspect.EARTH, 7).add(Aspect.ORDER, 7).add(Aspect.ENTROPY, 7);
        PolyRecipes.addShapedArcaneRecipe("CAP_ELEMENTIUM.1","CAP_ELEMENTIUM@1", new ItemStack(PolyItems.itemWandCap, 1, 2), 10, crystals, "NNN","N N",'N',"nuggetElvenElementium");

        AspectList aspects;
        aspects = new AspectList().add(Aspect.EARTH, 20).add(Aspect.FIRE, 20).add(Aspect.WATER, 20).add(Aspect.AIR, 20).add(Aspect.MAGIC, 20);
        PolyRecipes.addInfusionRecipe("CAP_ELEMENTIUM.2", "CAP_ELEMENTIUM@2", new ItemStack(PolyItems.itemWandCap, 1, 3), 3, new ItemStack(PolyItems.itemWandCap, 1, 2), aspects, new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3), new ItemStack(manaResource,1,5));

        aspects = new AspectList().add(Aspect.PLANT, 64).add(Aspect.LIFE, 25).add(Aspect.SENSES, 20).add(Aspect.MAGIC, 10);
        PolyRecipes.addInfusionRecipe("ROD_LIVINGWOOD.1", "ROD_LIVINGWOOD@1", new ItemStack(PolyItems.itemWandRod, 1, 2), 3, new ItemStack(ModBlocks.livingwood), aspects, new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3), new ItemStack(rune, 1, 4), new ItemStack(rune, 1, 5), new ItemStack(rune, 1, 6), new ItemStack(rune, 1, 7));
        PolyRecipes.addInfusionRecipe("ROD_DREAMWOOD.1", "ROD_DREAMWOOD@1", new ItemStack(PolyItems.itemWandRod, 1, 4), 3, new ItemStack(ModBlocks.dreamwood), aspects,  new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3), new ItemStack(rune, 1, 4), new ItemStack(rune, 1, 5), new ItemStack(rune, 1, 6), new ItemStack(rune, 1, 7));

        BotaniaAPI.registerManaInfusionRecipe(new ItemStack(PolyItems.itemWandCap, 1, 1), new ItemStack(PolyItems.itemWandCap, 1, 0), 1000);
        BotaniaAPI.registerManaInfusionRecipe(new ItemStack(PolyItems.itemWandRod, 1, 3), new ItemStack(PolyItems.itemWandRod, 1, 2), 10000);
        BotaniaAPI.registerManaInfusionRecipe(new ItemStack(PolyItems.itemWandRod, 1, 5), new ItemStack(PolyItems.itemWandRod, 1, 4), 10000);

    }

    @Override
    public void initResearch() {

        // Botania
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.BOTANIA.stage.0")
                        .build()
        };
        parents = new String[] {"FIRSTSTEPS"};
        ResearchHelper.makeResearch("BOTANIA", "POLYMANCY", 3, 6, new ItemStack(ModItems.lexicon), stages, parents, ROUND, AUTOUNLOCK);

        // Mana Well
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.MANAWELL.stage.0")
                        .build()
        };
        parents = new String[] {"BOTANIA"};
        ResearchHelper.makeResearch("MANAWELL", "POLYMANCY", 4, 4, new ItemStack(ModItems.manaInkwell), stages, parents, AUTOUNLOCK);


        // Livingwood Rod
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.ROD_LIVINGWOOD.stage.0")
                        .setConsumedItems(PolyCompat.getItem("botania:livingwood", 0), new ItemStack(ModItems.rune, 1, 8))
                        .setKnow(new Knowledge(THEORY, PolyCompat.getCategory("POLYMANCY"), 1), new Knowledge(THEORY, PolyCompat.getCategory("THAUMATURGY"), 2))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.ROD_LIVINGWOOD.stage.1")
                        .setRecipes(recipes.get("ROD_LIVINGWOOD.1"))
                        .build()
        };
        parents = new String[] {"BOTANIA", "ROD_GREATWOOD"};
        ResearchHelper.makeResearch("ROD_LIVINGWOOD", "POLYMANCY", 5, 6, new ItemStack(PolyItems.itemWandRod, 1, 3), stages, parents);

        // Dreamwood Rod
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.ROD_DREAMWOOD.stage.0")
                        .setConsumedItems(new ItemStack(ModBlocks.dreamwood), new ItemStack(ModItems.manaResource, 1, 9))
                        .setKnow(new Knowledge(THEORY, PolyCompat.getCategory("POLYMANCY"), 2), new Knowledge(THEORY, PolyCompat.getCategory("THAUMATURGY"), 2))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.ROD_DREAMWOOD.stage.1")
                        .setRecipes(recipes.get("ROD_DREAMWOOD.1"))
                        .build()
        };
        parents = new String[] {"ROD_LIVINGWOOD", "ROD_SILVERWOOD"};
        ResearchHelper.makeResearch("ROD_DREAMWOOD", "POLYMANCY", 8, 6, new ItemStack(PolyItems.itemWandRod, 1, 5), stages, parents);

        // Manasteel Cap
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.CAP_MANASTEEL.stage.0")
                        .setRequiredCraft(new ItemStack(ModItems.manaResource, 1, 17))
                        .setKnow(new Knowledge(OBSERVATION, PolyCompat.getCategory("POLYMANCY"), 1), new Knowledge(THEORY, PolyCompat.getCategory("THAUMATURGY"), 1))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.CAP_MANASTEEL.stage.1")
                        .setRecipes(recipes.get("CAP_MANASTEEL.1"))
                        .setRequiredCraft(new ItemStack(PolyItems.itemWandCap,1,1))
                        .build()
        };
        parents = new String[] {"ROD_LIVINGWOOD", "CAP_THAUMIUM"};
        ResearchHelper.makeResearch("CAP_MANASTEEL", "POLYMANCY", 6, 5, new ItemStack(PolyItems.itemWandCap, 1, 1), stages, parents);

        // Elementium Cap
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.CAP_ELEMENTIUM.stage.0")
                        .setRequiredCraft(new ItemStack(ModItems.manaResource, 1, 19))
                        .setKnow(new Knowledge(THEORY, PolyCompat.getCategory("POLYMANCY"), 1), new Knowledge(THEORY, PolyCompat.getCategory("THAUMATURGY"), 2))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.CAP_ELEMENTIUM.stage.1")
                        .setRecipes(recipes.get("CAP_ELEMENTIUM.1"),recipes.get("CAP_ELEMENTIUM.2"))
                        .build()
        };
        parents = new String[] {"CAP_MANASTEEL"};
        ResearchHelper.makeResearch("CAP_ELEMENTIUM", "POLYMANCY", 8, 5, new ItemStack(PolyItems.itemWandCap, 1, 3), stages, parents);

        // Terrasteel Caps
        stages = new ResearchStage[] {
                new ResearchHelper.RSB()
                        .setText("research.CAP_TERRASTEEL.stage.0")
                        .setRequiredCraft(new ItemStack(ModItems.manaResource, 1, 18))
                        .setKnow(new Knowledge(OBSERVATION, PolyCompat.getCategory("POLYMANCY"), 1), new Knowledge(THEORY, PolyCompat.getCategory("THAUMATURGY"), 1))
                        .build(),
                new ResearchHelper.RSB()
                        .setText("research.CAP_TERRASTEEL.stage.1")
                        .setRecipes(recipes.get("CAP_TERRASTEEL.1"))
                        .build()
        };
        parents = new String[] {"CAP_MANASTEEL"};
        ResearchHelper.makeResearch("CAP_TERRASTEEL", "POLYMANCY", 7, 4, new ItemStack(PolyItems.itemWandCap, 1, 4), stages, parents);

        ResearchHelper.registerCards(CardAlfheim.class, CardMana.class, CardPetal.class, CardRune.class);

        ResearchHelper.makeAid(ModBlocks.pool, CardMana.class);
        ResearchHelper.makeAid(ModBlocks.alfPortal, CardAlfheim.class);
        ResearchHelper.makeAid(ModBlocks.altar, CardPetal.class);
        ResearchHelper.makeAid(ModBlocks.runeAltar, CardRune.class);

    }

}
