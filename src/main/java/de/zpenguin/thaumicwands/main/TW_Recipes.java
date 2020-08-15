package de.zpenguin.thaumicwands.main;

import java.util.HashMap;

import de.zpenguin.thaumicwands.compat.TW_Compat;
import de.zpenguin.thaumicwands.crafting.recipe.RecipeWand;
import de.zpenguin.thaumicwands.item.TW_Items;
import de.zpenguin.thaumicwands.util.WandHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.RegistryManager;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

public class TW_Recipes {

    public static HashMap<String, ResourceLocation> recipes = new HashMap<>();

	public static void registerRecipes(Register<IRecipe> r) {
		addCraftingRecipes();
		addArcaneRecipes(r);
		addInfusionRecipes();

		TW_Compat.initRecipes();

		RegistryManager.ACTIVE.getRegistry(GameData.RECIPES).remove(new ResourceLocation("thaumcraft:thaumometer"));
	}

	private static void addCraftingRecipes() {
		 addShapedOreRecipe("BASETHAUMATURGY.1", new ItemStack(TW_Items.itemWandCap, 1,0), "NNN", "N N", 'N', "nuggetIron");
		 addShapedOreRecipe("BASETHAUMATURGY.2", WandHelper.getWandWithParts("wood", "iron"), "  C", " R ","C  ",'C', new ItemStack(TW_Items.itemWandCap), 'R', Items.STICK);

	}

	private static void addArcaneRecipes(Register<IRecipe> r) {
        AspectList crystals;

        crystals = new AspectList().add(Aspect.AIR,1).add(Aspect.FIRE,1).add(Aspect.WATER,1).add(Aspect.EARTH,1).add(Aspect.ORDER,1).add(Aspect.ENTROPY,1);
        addShapedArcaneRecipe("FIRSTSTEPS.1","FIRSTSTEPS@2", new ItemStack(ItemsTC.thaumometer), 0, crystals, " G ","GPG"," G ", 'G',"ingotGold", 'P', Blocks.GLASS_PANE);

        crystals = new AspectList().add(Aspect.AIR,1).add(Aspect.FIRE,1).add(Aspect.WATER,1).add(Aspect.EARTH,1).add(Aspect.ORDER,1).add(Aspect.ENTROPY,1);
        addShapedArcaneRecipe("PRIMALCHARM.1","PRIMALCHARM@1", new ItemStack(TW_Items.itemPrimalCharm), 20, crystals, "AFW","BRB","EOP", 'A', ThaumcraftApiHelper.makeCrystal(Aspect.AIR), 'F', ThaumcraftApiHelper.makeCrystal(Aspect.FIRE), 'W', ThaumcraftApiHelper.makeCrystal(Aspect.WATER),'B',"ingotBrass", 'R', new ItemStack(ItemsTC.visResonator), 'E', ThaumcraftApiHelper.makeCrystal(Aspect.EARTH), 'O', ThaumcraftApiHelper.makeCrystal(Aspect.ORDER), 'P', ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY));

        crystals = new AspectList().add(Aspect.AIR,2).add(Aspect.FIRE,2).add(Aspect.ORDER,2);
        addShapedArcaneRecipe("CAP_COPPER.1","CAP_COPPER@1", new ItemStack(TW_Items.itemWandCap, 1, 1), 10, crystals, "NNN","N N",'N',"nuggetCopper");

        crystals = new AspectList().add(Aspect.AIR,3).add(Aspect.FIRE,3).add(Aspect.ORDER,3);
        addShapedArcaneRecipe("CAP_BRASS.1","CAP_BRASS@1", new ItemStack(TW_Items.itemWandCap, 1, 2), 10, crystals, "NNN","N N",'N',"nuggetBrass");

        crystals = new AspectList().add(Aspect.AIR,4).add(Aspect.FIRE,4).add(Aspect.ORDER,4);
        addShapedArcaneRecipe("CAP_SILVER.1","CAP_SILVER@1", new ItemStack(TW_Items.itemWandCap, 1, 3), 10, crystals, "NNN","N N",'N',"nuggetSilver");

        crystals = new AspectList().add(Aspect.AIR,5).add(Aspect.FIRE,5).add(Aspect.ORDER,5);
        addShapedArcaneRecipe("CAP_THAUMIUM.1","CAP_THAUMIUM@1", new ItemStack(TW_Items.itemWandCap, 1, 5), 10, crystals, "NNN","N N",'N',"nuggetThaumium");

        crystals = new AspectList().add(Aspect.AIR,7).add(Aspect.FIRE,7).add(Aspect.ORDER,7).add(Aspect.ENTROPY, 7);
        addShapedArcaneRecipe("CAP_VOID.1","CAP_VOID@1", new ItemStack(TW_Items.itemWandCap, 1, 7), 10, crystals, "NNN","N N",'N',"nuggetVoid");

        crystals = new AspectList().add(Aspect.ENTROPY, 2);
        addShapedArcaneRecipe("ROD_GREATWOOD.1","ROD_GREATWOOD@1", new ItemStack(TW_Items.itemWandRod, 1, 0), 10, crystals, "  L"," L ","L  ",'L', new ItemStack(BlocksTC.logGreatwood));
		r.getRegistry().register(new RecipeWand(getNameForRecipe(new ItemStack(TW_Items.itemWand))));

	}

	private static void addInfusionRecipes() {
        AspectList aspects;

        aspects = new AspectList().add(Aspect.ENERGY, 30).add(Aspect.AURA, 15).add(Aspect.MAGIC, 15);
        addInfusionRecipe("CAP_SILVER.2","CAP_SILVER@1", new ItemStack(TW_Items.itemWandCap,1,4), 3, new ItemStack(TW_Items.itemWandCap,1,3), aspects, new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus));

        aspects = new AspectList().add(Aspect.ENERGY, 50).add(Aspect.AURA, 25).add(Aspect.MAGIC, 25);
        addInfusionRecipe("CAP_THAUMIUM.2","CAP_THAUMIUM@1", new ItemStack(TW_Items.itemWandCap,1,6), 3, new ItemStack(TW_Items.itemWandCap,1,5), aspects, new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus));

        aspects = new AspectList().add(Aspect.ENERGY, 50).add(Aspect.VOID,50).add(Aspect.AURA, 25).add(Aspect.ELDRITCH, 25);
        addInfusionRecipe("CAP_VOID.2", "CAP_VOID@1", new ItemStack(TW_Items.itemWandCap,1,8), 3, new ItemStack(TW_Items.itemWandCap,1,7), aspects, new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.salisMundus));


        aspects = new AspectList().add(Aspect.AIR, 50).add(Aspect.MAGIC, 25).add(Aspect.MOTION, 25);
        addInfusionRecipe("ROD_REED.1","ROD_REED@1", new ItemStack(TW_Items.itemWandRod,1,1), 3, new ItemStack(Items.REEDS), aspects, ThaumcraftApiHelper.makeCrystal(Aspect.AIR),ThaumcraftApiHelper.makeCrystal(Aspect.MOTION));

        aspects = new AspectList().add(Aspect.FIRE, 50).add(Aspect.MAGIC, 25).add(Aspect.BEAST, 25);
        addInfusionRecipe("ROD_BLAZE.1","ROD_BLAZE@1", new ItemStack(TW_Items.itemWandRod,1,2), 3, new ItemStack(Items.BLAZE_ROD), aspects, ThaumcraftApiHelper.makeCrystal(Aspect.FIRE),ThaumcraftApiHelper.makeCrystal(Aspect.BEAST));

        aspects = new AspectList().add(Aspect.WATER, 50).add(Aspect.MAGIC, 25).add(Aspect.COLD, 25);
        addInfusionRecipe("ROD_ICE.1","ROD_ICE@1", new ItemStack(TW_Items.itemWandRod,1,3), 3, new ItemStack(Blocks.ICE), aspects, ThaumcraftApiHelper.makeCrystal(Aspect.WATER),ThaumcraftApiHelper.makeCrystal(Aspect.COLD));

        aspects = new AspectList().add(Aspect.EARTH, 50).add(Aspect.MAGIC, 25).add(Aspect.DARKNESS, 25);
        addInfusionRecipe("ROD_OBSIDIAN.1","ROD_OBSIDIAN@1", new ItemStack(TW_Items.itemWandRod,1,4), 3, new ItemStack(Blocks.OBSIDIAN), aspects, ThaumcraftApiHelper.makeCrystal(Aspect.EARTH),ThaumcraftApiHelper.makeCrystal(Aspect.DARKNESS));

        aspects = new AspectList().add(Aspect.ORDER, 50).add(Aspect.MAGIC, 25).add(Aspect.CRYSTAL, 25);
        addInfusionRecipe("ROD_QUARTZ.1","ROD_QUARTZ@1", new ItemStack(TW_Items.itemWandRod,1,5), 3, new ItemStack(Blocks.QUARTZ_BLOCK), aspects, ThaumcraftApiHelper.makeCrystal(Aspect.ORDER),ThaumcraftApiHelper.makeCrystal(Aspect.CRYSTAL));

        aspects = new AspectList().add(Aspect.ENTROPY, 50).add(Aspect.MAGIC, 25).add(Aspect.UNDEAD, 25);
        addInfusionRecipe("ROD_BONE.1","ROD_BONE@1", new ItemStack(TW_Items.itemWandRod,1,6), 3, new ItemStack(Items.BONE), aspects, ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY),ThaumcraftApiHelper.makeCrystal(Aspect.UNDEAD));

        aspects = new AspectList().add(Aspect.AIR, 25).add(Aspect.FIRE, 25).add(Aspect.WATER, 25).add(Aspect.EARTH, 25).add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.MAGIC, 25).add(Aspect.AURA,25);
        addInfusionRecipe("ROD_SILVERWOOD.1","ROD_SILVERWOOD@1", new ItemStack(TW_Items.itemWandRod,1,7), 5, new ItemStack(BlocksTC.logSilverwood), aspects, ThaumcraftApiHelper.makeCrystal(Aspect.AIR), ThaumcraftApiHelper.makeCrystal(Aspect.FIRE), ThaumcraftApiHelper.makeCrystal(Aspect.WATER), ThaumcraftApiHelper.makeCrystal(Aspect.EARTH), ThaumcraftApiHelper.makeCrystal(Aspect.ORDER), ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY), ThaumcraftApiHelper.makeCrystal(Aspect.MAGIC));

	}

    public static void addShapedOreRecipe(String name,ItemStack output, Object... params) {
        ResourceLocation location = new ResourceLocation(ThaumicWands.modID, name);
        ShapedOreRecipe recipe = new ShapedOreRecipe(location, output, params);
        recipe.setRegistryName(location);
        ForgeRegistries.RECIPES.register(recipe);
        recipes.put(name, location);
    }

    public static void addShapedArcaneRecipe(String name, String research, ItemStack output, int vis, AspectList crystals, Object... input) {
        ResourceLocation location = new ResourceLocation(ThaumicWands.modID, name);
        ShapedArcaneRecipe recipe = new ShapedArcaneRecipe(location, research, vis, crystals, output, input);
        ThaumcraftApi.addArcaneCraftingRecipe(location, recipe);
        recipes.put(name, location);
    }

    public static void addInfusionRecipe(String name, String research, ItemStack output, int instability, ItemStack catalyst, AspectList aspects, Object... input) {
        ResourceLocation location = new ResourceLocation(ThaumicWands.modID, name);
        InfusionRecipe recipe = new InfusionRecipe(research, output, instability, aspects, catalyst, input);
        ThaumcraftApi.addInfusionCraftingRecipe(location, recipe);
        recipes.put(name, location);
    }

    public static ResourceLocation getNameForRecipe(ItemStack output) {
        ModContainer activeContainer = Loader.instance().activeModContainer();
        ResourceLocation baseLoc = new ResourceLocation(activeContainer.getModId(), output.getItem().getUnlocalizedName());
        ResourceLocation recipeLoc = baseLoc;
        int index = 0;
        while (CraftingManager.REGISTRY.containsKey(recipeLoc)) {
            index++;
            recipeLoc = new ResourceLocation(activeContainer.getModId(), baseLoc.getResourcePath() + "_" + index);
        }
        return recipeLoc;
    }

}
