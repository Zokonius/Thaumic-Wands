package de.zpenguin.thaumicwands.item;

import net.minecraft.item.Item;
import thaumcraft.common.config.ConfigItems;

public class ItemBase extends Item {

    public ItemBase(String name) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ConfigItems.TABTC);
        TW_Items.ITEMS.add(this);
    }

}
