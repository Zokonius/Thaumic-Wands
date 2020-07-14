package de.zpenguin.thaumicwands.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemBaseMeta extends ItemBase {

    protected final String baseName;
    protected String[] variants;

    public ItemBaseMeta(String name, String... variants) {
        super(name);
        this.setHasSubtypes(variants.length > 0);
        baseName = name;
        this.variants = variants;

    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH)
            for (int i = 0; !(i == variants.length); i++)
                items.add(new ItemStack(this, 1, i));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.getMetadata() > variants.length)
            stack.setItemDamage(0);
        return baseName + "_" + variants[stack.getItemDamage()];
    }

    public String getBaseName() {
        return baseName;
    }

    public String[] getVariants() {
        return variants;
    }

}
