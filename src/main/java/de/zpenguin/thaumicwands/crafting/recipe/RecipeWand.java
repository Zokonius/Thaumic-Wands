package de.zpenguin.thaumicwands.crafting.recipe;

import de.zpenguin.thaumicwands.api.ThaumicWandsAPI;
import de.zpenguin.thaumicwands.api.item.wand.IWandCap;
import de.zpenguin.thaumicwands.api.item.wand.IWandRod;
import de.zpenguin.thaumicwands.api.recipe.IPlayerDependentArcaneRecipe;
import de.zpenguin.thaumicwands.util.WandWrapper;
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
import thaumcraft.api.crafting.IArcaneWorkbench;
import thaumcraft.api.items.ItemsTC;

public class RecipeWand extends Impl<IRecipe> implements IPlayerDependentArcaneRecipe {

	private WandWrapper wrapper = new WandWrapper();

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
		if(!getParts(inv).isValidWand())
			return false;
		if(!wrapper.canCraft(player))
			return false;
		if(!checkCrystals(inv))
			return false;
		return true;

	}

	private WandWrapper getParts(InventoryCrafting inv) {

		wrapper = new WandWrapper();

		if(inv.getStackInSlot(2).isEmpty() || inv.getStackInSlot(6).isEmpty() || inv.getStackInSlot(4).isEmpty())
			return wrapper;

		if(!ItemStack.areItemsEqual(inv.getStackInSlot(2), inv.getStackInSlot(6)))
			return wrapper;


		for(IWandCap wc : ThaumicWandsAPI.wandCaps.values())
			if(ItemStack.areItemsEqual(inv.getStackInSlot(2), wc.getItemStack())) {
				wrapper.setCap(wc);
				break;
			}

		for(IWandRod wr : ThaumicWandsAPI.wandRods.values())
			if(ItemStack.areItemsEqual(inv.getStackInSlot(4), wr.getItemStack())) {
				wrapper.setRod(wr);
				break;
			}


		return wrapper;

	}

	private boolean checkCrystals(InventoryCrafting inv) {
		if(inv.getSizeInventory() >= 15)
			for(Aspect aspect : getCrystals().getAspects()) {
				ItemStack cs = ThaumcraftApiHelper.makeCrystal(aspect, getCrystals().getAmount(aspect));
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
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return getParts(inv).isValidWand() ? getParts(inv).makeWand() : ItemStack.EMPTY;
	}

	@Override
	public AspectList getCrystals() {
		return wrapper.getCrystals();
	}

	@Override
	public int getVis() {
		return wrapper.getVisCost();
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
