package com.interplay.thaumicwandsremastered.container;

import com.interplay.thaumicwandsremastered.api.IWand;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotWand extends Slot {

    public SlotWand(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack!=null && stack.getItem() instanceof IWand;
    }

}
