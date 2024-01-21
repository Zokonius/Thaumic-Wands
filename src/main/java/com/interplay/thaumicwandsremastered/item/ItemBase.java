package com.interplay.thaumicwandsremastered.item;

import net.minecraft.item.Item;
import thaumcraft.common.config.ConfigItems;

public class ItemBase extends Item {

    public ItemBase(String name) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ConfigItems.TABTC);
        TWR_Items.ITEMS.add(this);
    }

}
