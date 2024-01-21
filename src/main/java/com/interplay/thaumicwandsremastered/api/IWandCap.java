package com.interplay.thaumicwandsremastered.api;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;

public interface IWandCap {

    ResourceLocation getTexture();

    String getRequiredResearch();

    ItemStack getItemStack();

    String getTag();

    int getCraftCost();

    float getDiscount();

    AspectList getAspectDiscount();

}
