package de.zpenguin.thaumicwands.inventory;

import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import thaumcraft.common.container.InventoryArcaneWorkbench;

public class InventoryArcaneWorkbenchNew extends InventoryArcaneWorkbench {

	private final NonNullList<ItemStack> stackList;
	private int inventoryHeight = 3;
	private int inventoryWidth = 5;
	public Container eventHandler;

	public InventoryArcaneWorkbenchNew(TileEntity tileEntity, Container container) {
		super(tileEntity, container);
		this.stackList = NonNullList.withSize(16, ItemStack.EMPTY);
        this.eventHandler = container;
	}

    public int getSizeInventory() {
        return this.stackList.size();
    }

    public boolean isEmpty() {
        for (ItemStack itemstack : this.stackList)
        	if (!itemstack.isEmpty())
                return false;
        return true;
    }

    public ItemStack getStackInSlot(int index) {
        return index >= this.getSizeInventory() ? ItemStack.EMPTY : (ItemStack)this.stackList.get(index);
    }

    public ItemStack getStackInRowAndColumn(int row, int column) {
        return row >= 0 && row < this.inventoryWidth && column >= 0 && column <= this.inventoryHeight ? this.getStackInSlot(row + column * this.inventoryWidth) : ItemStack.EMPTY;
    }

    public ItemStack removeStackFromSlot(int index) {
    	ItemStack stack = ItemStackHelper.getAndRemove(this.stackList, index);
        this.eventHandler.onCraftMatrixChanged(this);
        return stack;
    }

    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.stackList, index, count);

        if (!itemstack.isEmpty())
            this.eventHandler.onCraftMatrixChanged(this);

        return itemstack;
    }

    public void setInventorySlotContents(int index, ItemStack stack) {
        this.stackList.set(index, stack);
        this.eventHandler.onCraftMatrixChanged(this);
    }

    public void clear() {
        this.stackList.clear();
    }

    public int getHeight() {
        return this.inventoryHeight;
    }

    public int getWidth() {
        return this.inventoryWidth;
    }

    public void fillStackedContents(RecipeItemHelper helper) {
        for (ItemStack itemstack : this.stackList)
        	helper.accountStack(itemstack);
    }

}
