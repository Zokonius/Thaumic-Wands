package de.zpenguin.thaumicwands.api.recipe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IArcaneRecipe;

public interface IPlayerDependentArcaneRecipe extends IArcaneRecipe {

	public boolean matches(InventoryCrafting inv, World world, EntityPlayer player);

}
