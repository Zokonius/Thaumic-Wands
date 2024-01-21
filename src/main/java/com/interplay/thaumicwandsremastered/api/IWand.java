package com.interplay.thaumicwandsremastered.api;

import net.minecraft.item.ItemStack;
import thaumcraft.api.casters.ICaster;
import thaumcraft.api.items.IArchitect;

public interface IWand extends ICaster, IArchitect, IFractionalVis {

    IWandCap getCap(ItemStack stack);

    IWandRod getRod(ItemStack stack);

}
