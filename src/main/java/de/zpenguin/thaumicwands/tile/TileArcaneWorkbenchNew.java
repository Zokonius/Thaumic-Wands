package de.zpenguin.thaumicwands.tile;

import de.zpenguin.thaumicwands.inventory.InventoryArcaneWorkbenchNew;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.ContainerDummy;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
import thaumcraft.common.world.aura.AuraHandler;

public class TileArcaneWorkbenchNew extends TileArcaneWorkbench implements ITickable {

	public int auraChunkX, auraChunkZ = -1;

	public TileArcaneWorkbenchNew() {
		inventoryCraft = new InventoryArcaneWorkbenchNew(this, new ContainerDummy());
	}

	@Override
	public void update() {
		if(!world.isRemote) {
			if(world.getTotalWorldTime() % 30 == 0)
				if(hasCharger() && !getWand().isEmpty()) {
					rechargeWand();
				}
			syncWand();

		}

	}

	private boolean hasCharger() {
		return world.getBlockState(getPos().up()).getBlock() == BlocksTC.arcaneWorkbenchCharger;
	}

	private ItemStack getWand() {
		return inventoryCraft.getStackInSlot(15);
	}

	private void syncWand() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag("wandStack", inventoryCraft.getStackInSlot(15).writeToNBT(new NBTTagCompound()));
		sendMessageToClient(nbt, null);
	}

	@Override
	public void messageFromServer(NBTTagCompound nbt) {
		super.messageFromServer(nbt);
		if(nbt.hasKey("wandStack"))
			this.inventoryCraft.setInventorySlotContents(15, new ItemStack(nbt.getCompoundTag("wandStack")));

	}

	public void rechargeWand() {
		if(RechargeHelper.getChargePercentage(getWand(), null) < 1F)
		AuraHandler.drainVis(getWorld(), getPos().add(auraChunkX * 16, 0, auraChunkZ * 16), 1, false);
		auraChunkX++;
		if(auraChunkX>1) {
			auraChunkX = -1;
			auraChunkZ++;
			if(auraChunkZ >1)
				auraChunkZ = auraChunkX;
		}

		RechargeHelper.rechargeItemBlindly(getWand(), null, 1);

	}

}
