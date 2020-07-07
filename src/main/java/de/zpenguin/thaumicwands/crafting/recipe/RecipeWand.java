package de.zpenguin.thaumicwands.crafting.recipe;

import de.zpenguin.thaumicwands.api.ThaumicWandsAPI;
import de.zpenguin.thaumicwands.api.item.wand.IWandCap;
import de.zpenguin.thaumicwands.api.item.wand.IWandRod;
import de.zpenguin.thaumicwands.api.recipe.IPlayerDependentArcaneRecipe;
import de.zpenguin.thaumicwands.util.WandHelper;
import de.zpenguin.thaumicwands.wand.TW_Wands;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.crafting.IArcaneWorkbench;
import thaumcraft.api.items.ItemsTC;

public class RecipeWand extends Impl<IRecipe> implements IPlayerDependentArcaneRecipe {

	private IWandRod rod = null;
	private IWandCap cap = null;

	public RecipeWand(ResourceLocation name) {
		this.setRegistryName(name);
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world, EntityPlayer player) {
		if(!(inv instanceof IArcaneWorkbench))
			return false;
		int[] empty = {0, 1, 3, 5, 7, 8};
		for(int i : empty)
			if(!inv.getStackInSlot(i).isEmpty())
				return false;
		if(!checkItems(inv.getStackInSlot(2), inv.getStackInSlot(6), inv.getStackInSlot(4), player))
			return false;
		if(!checkCrystals(inv))
			return false;
		return true;
	}

	private boolean checkItems(ItemStack cap1, ItemStack cap2, ItemStack rod, EntityPlayer player) {
		if(cap1.isEmpty() || cap2.isEmpty() || rod.isEmpty())
			return false;

		if(ItemStack.areItemsEqual(cap1, cap2)) {
			for(IWandCap wc : ThaumicWandsAPI.wandCaps.values())
				if(ItemStack.areItemsEqual(cap1, wc.getItemStack())) {
					if(ThaumcraftCapabilities.knowsResearch(player, wc.getRequiredResearch())) {
						this.cap = wc;
						break;
					}
				}
			for(IWandRod wr : ThaumicWandsAPI.wandRods.values())
				if(ItemStack.areItemsEqual(rod, wr.getItemStack())
						&& ThaumcraftCapabilities.knowsResearch(player, wr.getRequiredResearch())) {
					this.rod = wr;
					break;
				}

		}
		return this.rod != null && this.cap != null && (this.rod.getTag() != "wood" || this.cap.getTag() != "iron");
	}

	private boolean checkCrystals(InventoryCrafting inv) {
		if(this.getCrystals() != null && inv.getSizeInventory() >= 15)
			for(Aspect aspect : this.getCrystals().getAspects()) {
				ItemStack cs = ThaumcraftApiHelper.makeCrystal(aspect, this.getCrystals().getAmount(aspect));
				boolean b = false;
				for(int i = 0; i < 6; i++) {
					ItemStack itemstack1 = inv.getStackInSlot(9 + i);
					if(itemstack1 != null && itemstack1.getItem() == ItemsTC.crystalEssence
							&& itemstack1.getCount() >= cs.getCount()
							&& ItemStack.areItemStackTagsEqual(cs, itemstack1))
						b = true;
				}
				if(!b)
					return false;
			}
		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		return rod == null || cap == null ? WandHelper.getWandWithParts("wood", "iron") : WandHelper.getWandWithParts(rod.getTag(), cap.getTag());
	}

	@Override
	public AspectList getCrystals() {
		AspectList list = new AspectList();
		if(rod == null || cap == null)
			return list;
		for(Aspect a : Aspect.getPrimalAspects())
			list.add(a, Math.max(rod.getCraftCost(), cap.getCraftCost()));
		return list;
	}

	@Override
	public int getVis() {
		if(rod == null || cap == null)
			return 0;
		if(rod == TW_Wands.rodWood && cap == TW_Wands.capIron)
			return 0;
		return rod.getCraftCost() * cap.getCraftCost();
	}

	@Override
	public String getResearch() {
		return "";
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		return false;
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height == 9;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> items = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
		for(int i = 9; i != inv.getSizeInventory(); i++)
			items.set(i, inv.getStackInSlot(i));

		return items;
	}

}
