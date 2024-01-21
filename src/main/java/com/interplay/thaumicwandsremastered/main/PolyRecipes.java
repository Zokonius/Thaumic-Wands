package com.interplay.thaumicwandsremastered.main;

import com.interplay.thaumicwandsremastered.compat.PolyCompat;
import com.interplay.thaumicwandsremastered.item.PolyItems;
import com.interplay.thaumicwandsremastered.item.TWR_Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

import java.util.HashMap;

public class PolyRecipes {

    public static HashMap<String, ResourceLocation> recipes = new HashMap<>();

    public static void registerRecipes(RegistryEvent.Register<IRecipe> r) {

        AspectList crystals;
        crystals = new AspectList().add(Aspect.AIR, 5).add(Aspect.FIRE, 5).add(Aspect.WATER, 5).add(Aspect.EARTH, 5).add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 5);
        addShapelessArcaneRecipe("PRIMEWELL.1","PRIMEWELL@1", new ItemStack(PolyItems.itemPrimewell), 50, crystals, "feather", new ItemStack(ItemsTC.phial), Ingredient.fromItem(ItemsTC.primordialPearl));

        AspectList aspects;
        aspects = new AspectList().add(Aspect.FIRE, 50).add(Aspect.MAGIC, 20).add(Aspect.AVERSION, 20);
        addInfusionRecipe("ROD_INFERNAL.1", "ROD_INFERNAL@2", new ItemStack(PolyItems.itemWandRod, 1, 0), 3, new ItemStack(TWR_Items.itemWandRod, 1, 2), aspects, new ItemStack(Blocks.NETHERRACK), new ItemStack(Items.NETHER_WART), new ItemStack(Items.NETHER_WART), new ItemStack(Items.SKULL,1,1), new ItemStack(Items.NETHER_STAR));

        aspects = new AspectList().add(Aspect.DARKNESS, 50).add(Aspect.ELDRITCH, 20).add(Aspect.AIR, 20);
        addInfusionRecipe("ROD_CHORUS.1", "ROD_CHORUS@2", new ItemStack(PolyItems.itemWandRod, 1, 1), 3, new ItemStack(TWR_Items.itemWandRod, 1, 4), aspects, new ItemStack(Blocks.END_STONE), new ItemStack(Items.CHORUS_FRUIT_POPPED), new ItemStack(Items.CHORUS_FRUIT_POPPED), new ItemStack(Items.ENDER_EYE), new ItemStack(Blocks.DRAGON_EGG));

        PolyCompat.initRecipes();

    }

    public static void addShapedOreRecipe(String name,ItemStack output, Object... params) {
        ResourceLocation location = new ResourceLocation(ThaumicWandsRemastered.MODID, name);
        ShapedOreRecipe recipe = new ShapedOreRecipe(location, output, params);
        recipe.setRegistryName(location);
        ForgeRegistries.RECIPES.register(recipe);
        recipes.put(name, location);
    }

    public static void addShapedArcaneRecipe(String name, String research, ItemStack output, int vis, AspectList crystals, Object... input) {
        ResourceLocation location = new ResourceLocation(ThaumicWandsRemastered.MODID, name);
        ShapedArcaneRecipe recipe = new ShapedArcaneRecipe(location, research, vis, crystals, output, input);
        ThaumcraftApi.addArcaneCraftingRecipe(location, recipe);
        recipes.put(name, location);
    }

    public static void addShapelessArcaneRecipe(String name, String research, ItemStack output, int vis, AspectList crystals, Object... input) {
        ResourceLocation location = new ResourceLocation(ThaumicWandsRemastered.MODID, name);
        ShapelessArcaneRecipe recipe = new ShapelessArcaneRecipe(location, research, vis, crystals, output, input);
        ThaumcraftApi.addArcaneCraftingRecipe(location, recipe);
        recipes.put(name, location);
    }

    public static void addInfusionRecipe(String name, String research, ItemStack output, int instability, ItemStack catalyst, AspectList aspects, Object... input) {
        ResourceLocation location = new ResourceLocation(ThaumicWandsRemastered.MODID, name);
        InfusionRecipe recipe = new InfusionRecipe(research, output, instability, aspects, catalyst, input);
        ThaumcraftApi.addInfusionCraftingRecipe(location, recipe);
        recipes.put(name, location);
    }

}
