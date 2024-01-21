package com.interplay.thaumicwandsremastered.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IWandUpdate {

    void onUpdate(ItemStack stack, EntityPlayer player);

}
