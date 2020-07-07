package de.zpenguin.thaumicwands.api.item.wand;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;

public interface IWandCap {

	public ResourceLocation getTexture();

	public String getRequiredResearch();

	public ItemStack getItemStack();

	public String getTag();

	public int getCraftCost();

	public float getDiscount();

	public AspectList getAspectDiscount();

}
