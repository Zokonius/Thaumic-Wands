package de.zpenguin.thaumicwands.container;

import de.zpenguin.thaumicwands.container.slot.SlotArcaneWorkbenchNew;
import de.zpenguin.thaumicwands.container.slot.SlotWand;
import de.zpenguin.thaumicwands.crafting.ThaumicWandsCraftingManager;
import de.zpenguin.thaumicwands.util.ReflectionHelper;
import de.zpenguin.thaumicwands.util.WandHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.ThaumcraftInvHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.crafting.ContainerDummy;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.common.blocks.world.ore.ShardType;
import thaumcraft.common.container.slot.SlotCrystal;
import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;

public class ContainerArcaneWorkbenchNew extends Container {

	private TileArcaneWorkbench tileEntity;
	private InventoryPlayer ip;
	public InventoryCraftResult craftResult = new InventoryCraftResult();
	public static int[] xx = new int[] {64, 17, 112, 17, 112, 64};
	public static int[] yy = new int[] {13, 35, 35, 93, 93, 115};

	public ContainerArcaneWorkbenchNew(InventoryPlayer inv, TileArcaneWorkbench e) {
		this.tileEntity = e;
		ReflectionHelper.setInventoryEventHandler(tileEntity.inventoryCraft, this);
		this.ip = inv;

		// Crafting Grid 0-8
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++)
				addSlotToContainer(new Slot(this.tileEntity.inventoryCraft, y + x * 3, 40 + y * 24, 40 + x * 24));

		// Shard Slots 9-14
		for(ShardType st : ShardType.values())
			if(st.getMetadata() < 6)
				addSlotToContainer(new SlotCrystal(st.getAspect(), this.tileEntity.inventoryCraft, st.getMetadata() + 9, xx[st.getMetadata()], yy[st.getMetadata()]));

		// Wand Slot 15
		addSlotToContainer(new SlotWand(this.tileEntity.inventoryCraft, 15, 160, 24));

		// Output Slot 16
		addSlotToContainer(new SlotArcaneWorkbenchNew(this.tileEntity, inv.player, this.tileEntity.inventoryCraft, (IInventory) this.craftResult, 16, 160, 64));

		//Player Inventory 17-43
		for(int x = 0; x < 9; x++)
			for(int y = 0; y < 3; y++)
				addSlotToContainer(new Slot(inv, x + y * 9 + 9, 16 + x * 18, 151 + y * 18));

		//Player Hotbar 44-53
		for(int x = 0; x < 9; x++)
			addSlotToContainer(new Slot(inv, x, 16 + x * 18, 209));


		onCraftMatrixChanged(this.tileEntity.inventoryCraft);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		onCraftMatrixChanged(this.tileEntity.inventoryCraft);
	}

	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory) {
		IArcaneRecipe recipe = ThaumicWandsCraftingManager.findMatchingArcaneRecipe(this.tileEntity.inventoryCraft, this.ip.player);
		boolean hasVis = true;
		boolean hasCrystals = true;

		if(recipe != null) {
			int vis = 0;
			AspectList crystals = null;
			vis = recipe.getVis();
			vis = WandHelper.getActualVisCost(vis, this.tileEntity.inventoryCraft.getStackInSlot(15), ip.player);
			crystals = recipe.getCrystals();
			hasVis = Math.max(0, RechargeHelper.getCharge(this.tileEntity.inventoryCraft.getStackInSlot(15))) >= vis;
			if(crystals != null && crystals.size() > 0)
				for(Aspect aspect : crystals.getAspects())
					if(ThaumcraftInvHelper.countTotalItemsIn(ThaumcraftInvHelper.wrapInventory(this.tileEntity.inventoryCraft, EnumFacing.UP),
							ThaumcraftApiHelper.makeCrystal(aspect, crystals.getAmount(aspect)),
							ThaumcraftInvHelper.InvFilter.STRICT) < crystals.getAmount(aspect)) {
						hasCrystals = false;
						break;
					}
		}

		if(hasVis && hasCrystals)
			slotChangedCraftingGrid(this.tileEntity.getWorld(), this.ip.player, this.tileEntity.inventoryCraft, this.craftResult);
		else {
			this.craftResult.setInventorySlotContents(0, ItemStack.EMPTY);
			if(!this.tileEntity.getWorld().isRemote)
			((EntityPlayerMP)this.ip.player).connection.sendPacket(new SPacketSetSlot(this.windowId, 16, ItemStack.EMPTY));
		}

		super.detectAndSendChanges();
	}

	@Override
	protected void slotChangedCraftingGrid(World world, EntityPlayer player, InventoryCrafting craftMat, InventoryCraftResult craftRes) {
		if(!world.isRemote) {
			EntityPlayerMP entityplayermp = (EntityPlayerMP) player;
			ItemStack itemstack = ItemStack.EMPTY;
			IArcaneRecipe arecipe = ThaumicWandsCraftingManager.findMatchingArcaneRecipe(craftMat, player);
			if(arecipe != null
					&& (arecipe.isDynamic() || !world.getGameRules().getBoolean("doLimitedCrafting")
							|| entityplayermp.getRecipeBook().isUnlocked(arecipe))
					&& ThaumcraftCapabilities.getKnowledge(player).isResearchKnown(arecipe.getResearch())) {
				craftRes.setRecipeUsed(arecipe);
				itemstack = arecipe.getCraftingResult(craftMat);
			} else {
				InventoryCrafting craftInv = new InventoryCrafting((Container) new ContainerDummy(), 3, 3);
				for(int a = 0; a < 9; a++)
					craftInv.setInventorySlotContents(a, craftMat.getStackInSlot(a));
				IRecipe irecipe = CraftingManager.findMatchingRecipe(craftInv, world);
				if(irecipe != null && (irecipe.isDynamic() || !world.getGameRules().getBoolean("doLimitedCrafting")
						|| entityplayermp.getRecipeBook().isUnlocked(irecipe))) {
					craftRes.setRecipeUsed(irecipe);
					itemstack = irecipe.getCraftingResult(craftMat);
				}
			}
			craftRes.setInventorySlotContents(0, itemstack);
			entityplayermp.connection.sendPacket(new SPacketSetSlot(this.windowId, 16, itemstack));
		}

	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
		if(!(this.tileEntity.getWorld()).isRemote)
			ReflectionHelper.setInventoryEventHandler(tileEntity.inventoryCraft, new ContainerDummy());
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return (this.tileEntity.getWorld().getTileEntity(this.tileEntity.getPos()) != this.tileEntity) ? false
				: ((par1EntityPlayer.getDistanceSqToCenter(this.tileEntity.getPos()) <= 64.0D));
	}



	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex) {
		ItemStack var2 = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(slotIndex);
		if(slot != null && slot.getHasStack()) {
			ItemStack var4 = slot.getStack();
			var2 = var4.copy();

			// Output
			if(slotIndex == 16) {
				if(!mergeItemStack(var4, 17, 53, true))
					return ItemStack.EMPTY;
				slot.onSlotChange(var4, var2);
			}

			// Insert Crystals
			else if(slotIndex >= 17 && slotIndex < 53) {
				for(ShardType st : ShardType.values()) {
					if(st.getMetadata() < 6 && SlotCrystal.isValidCrystal(var4, st.getAspect())) {
						if(!mergeItemStack(var4, 9 + st.getMetadata(), 10 + st.getMetadata(), false))
							return ItemStack.EMPTY;
						if(var4.getCount() == 0)
							break;
					}
				}
				if(var4.getCount() != 0)
					if(slotIndex >= 16 && slotIndex < 43) {
						if(!mergeItemStack(var4, 43, 52, false))
							return ItemStack.EMPTY;
					} else if(slotIndex >= 43 && slotIndex < 52) {
						if(!mergeItemStack(var4, 16, 43, false))
							return ItemStack.EMPTY;
					}
			} else if(!mergeItemStack(var4, 16, 52, false)) {
				return ItemStack.EMPTY;
			}
			if(var4.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			if(var4.getCount() == var2.getCount())
				return ItemStack.EMPTY;
			slot.onTake(this.ip.player, var4);
		}
		return var2;
	}

	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slot) {
		return(slot.inventory != this.craftResult && super.canMergeSlot(stack, slot));
	}

}
