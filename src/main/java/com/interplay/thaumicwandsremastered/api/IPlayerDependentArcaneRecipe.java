package com.interplay.thaumicwandsremastered.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IArcaneRecipe;

public interface IPlayerDependentArcaneRecipe extends IArcaneRecipe {

    boolean matches(InventoryCrafting inv, World world, EntityPlayer player);

}
