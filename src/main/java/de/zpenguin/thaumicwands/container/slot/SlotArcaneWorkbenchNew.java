package de.zpenguin.thaumicwands.container.slot;

import de.zpenguin.thaumicwands.api.recipe.IPlayerDependentArcaneRecipe;
import de.zpenguin.thaumicwands.crafting.ThaumicWandsCraftingManager;
import de.zpenguin.thaumicwands.util.ReflectionHelper;
import de.zpenguin.thaumicwands.util.WandHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ContainerDummy;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.common.container.slot.SlotCraftingArcaneWorkbench;
import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;

public class SlotArcaneWorkbenchNew extends SlotCraftingArcaneWorkbench {

	private final InventoryCrafting craftMatrix;
	private EntityPlayer player;

	public SlotArcaneWorkbenchNew(TileArcaneWorkbench te, EntityPlayer player, InventoryCrafting matrix, IInventory inventory, int index, int x, int y) {
		super(te, player, matrix, inventory, index, x, y);
		this.player = player;
		this.craftMatrix = matrix;
	}

	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
		super.onCrafting(stack);
		IArcaneRecipe recipe = ThaumicWandsCraftingManager.findMatchingArcaneRecipe(this.craftMatrix, thePlayer);
		InventoryCrafting ic = this.craftMatrix;
		NonNullList<ItemStack> nonnulllist;

		ForgeHooks.setCraftingPlayer(thePlayer);

		 if(recipe != null && recipe instanceof IPlayerDependentArcaneRecipe && ((IPlayerDependentArcaneRecipe) recipe).matches(craftMatrix, thePlayer.world, thePlayer))
				nonnulllist = recipe.getRemainingItems(craftMatrix);
		 else if(recipe != null)
			 	nonnulllist = CraftingManager.getRemainingItems(this.craftMatrix, thePlayer.world);
		 else {
			ic = new InventoryCrafting((Container) new ContainerDummy(), 3, 3);
			for(int i = 0; i < 9; i++)
				ic.setInventorySlotContents(i, this.craftMatrix.getStackInSlot(i));
			ReflectionHelper.setInventoryEventHandler(ic, ReflectionHelper.getInventoryEventHandler(craftMatrix));
			nonnulllist = CraftingManager.getRemainingItems(ic, thePlayer.world);
		}

		ForgeHooks.setCraftingPlayer(null);
		AspectList crystals = null;

		if(recipe != null) {
			int vis = recipe.getVis();
			vis = WandHelper.getActualVisCost(vis, craftMatrix.getStackInSlot(15), thePlayer);
			crystals = WandHelper.getActualCrystals(recipe.getCrystals(), craftMatrix.getStackInSlot(15));
			if(vis > 0)
				RechargeHelper.consumeCharge(this.craftMatrix.getStackInSlot(15), thePlayer, vis);
		}

		for(int i = 0; i < Math.min(9, nonnulllist.size()); i++) {
			ItemStack itemstack = ic.getStackInSlot(i);
			ItemStack itemstack1 = (ItemStack) nonnulllist.get(i);
			if(!itemstack.isEmpty()) {
				this.craftMatrix.decrStackSize(i, 1);
				itemstack = ic.getStackInSlot(i);
			}
			if(!itemstack1.isEmpty())
				if(itemstack.isEmpty())
					this.craftMatrix.setInventorySlotContents(i, itemstack1);
				else if(ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1)) {
					itemstack1.grow(itemstack.getCount());
					this.craftMatrix.setInventorySlotContents(i, itemstack1);
				}
				else if(!this.player.inventory.addItemStackToInventory(itemstack1))
					this.player.dropItem(itemstack1, false);

		}
		if(crystals != null)
			for(Aspect aspect : crystals.getAspects()) {
				ItemStack cs = ThaumcraftApiHelper.makeCrystal(aspect, crystals.getAmount(aspect));
				for(int j = 0; j < 6; j++) {
					ItemStack itemstack1 = this.craftMatrix.getStackInSlot(9 + j);
					if(itemstack1.getItem() == ItemsTC.crystalEssence
							&& ItemStack.areItemStackTagsEqual(cs, itemstack1))
						this.craftMatrix.decrStackSize(9 + j, cs.getCount());
				}
			}
		return stack;
	}

}
