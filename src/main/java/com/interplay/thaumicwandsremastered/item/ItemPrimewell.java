package com.interplay.thaumicwandsremastered.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;
import thaumcraft.api.items.IScribeTools;

public class ItemPrimewell extends ItemBase implements IScribeTools {

    public ItemPrimewell(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, 0);
    }

}
