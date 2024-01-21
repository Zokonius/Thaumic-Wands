package com.interplay.thaumicwandsremastered.wand;

import com.interplay.thaumicwandsremastered.api.IWandCap;
import com.interplay.thaumicwandsremastered.main.ThaumicWandsRemastered;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;

public class WandCap implements IWandCap {

    int craftCost;
    float discount;

    String tag;
    String research;
    AspectList aspectDiscount;
    ResourceLocation texture;
    ItemStack item;

    public WandCap(String tag, float discount, ItemStack item, int craftCost, String research) {
        this(tag, discount, new AspectList(), item, craftCost, research);
    }

    public WandCap(String tag, float discount, ItemStack item, int craftCost) {
        this(tag, discount, new AspectList(), item, craftCost, "CAP_" + tag.toUpperCase());
    }

    public WandCap(String tag, float discount, AspectList aspectDiscount, ItemStack item, int craftCost) {
        this(tag, discount, aspectDiscount, item, craftCost, "CAP_" + tag.toUpperCase());
    }

    public WandCap(String tag, float discount, AspectList aspectDiscount, ItemStack item, int craftCost, String research) {
        this.tag = tag;
        this.discount = discount;
        this.item = item;
        this.craftCost = craftCost;
        this.aspectDiscount = aspectDiscount;
        this.research = research;

        TWR_Wands.CAPS.add(this);
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(ThaumicWandsRemastered.MODID, "textures/models/wand_cap_" + tag + ".png");
    }

    public int getCraftCost() {
        return craftCost;
    }

    @Override
    public String getRequiredResearch() {
        return research;
    }

    @Override
    public ItemStack getItemStack() {
        return item;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public float getDiscount() {
        return discount;
    }

    @Override
    public AspectList getAspectDiscount() {
        return aspectDiscount;
    }

}
