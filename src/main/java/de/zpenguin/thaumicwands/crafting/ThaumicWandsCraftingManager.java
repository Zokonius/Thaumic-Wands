package de.zpenguin.thaumicwands.crafting;

import de.zpenguin.thaumicwands.api.recipe.IPlayerDependentArcaneRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.crafting.IArcaneRecipe;

public class ThaumicWandsCraftingManager {

	public static IArcaneRecipe findMatchingArcaneRecipe(InventoryCrafting matrix, EntityPlayer player) {
		for(ResourceLocation key : CraftingManager.REGISTRY.getKeys()) {
			IRecipe recipe = (IRecipe) CraftingManager.REGISTRY.getObject(key);
			if(recipe == null)
				return null;
			else if(recipe instanceof IPlayerDependentArcaneRecipe && ((IPlayerDependentArcaneRecipe) recipe).matches(matrix, player.world, player))
				return (IArcaneRecipe) recipe;
			else if(recipe instanceof IArcaneRecipe && recipe.matches(matrix, player.world))
				return (IArcaneRecipe) recipe;
		}
		return null;
	}

}
