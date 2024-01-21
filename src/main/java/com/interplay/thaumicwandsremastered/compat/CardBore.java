package com.interplay.thaumicwandsremastered.compat;

import com.interplay.thaumicwandsremastered.util.LocalizationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class CardBore extends TheorycraftCard {

    private static final ItemStack[] items = {PolyCompat.getItem("embers:dust_ember", 0), PolyCompat.getItem("embers:shard_ember", 2, 0), PolyCompat.getItem("embers:crystal_ember", 0)};

    private int id;

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setInteger("meta", id);
        return nbt;

    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        this.id = nbt.getInteger("meta");
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        id = player.getEntityWorld().rand.nextInt(items.length);
        return true;
    }

    @Override
    public String getResearchCategory() {
        return "POLYMANCY";
    }

    @Override
    public boolean isAidOnly() {
        return true;
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        data.addTotal(getResearchCategory(), 20);
        return true;
    }

    @Override
    public int getInspirationCost() {
        return 1;
    }

    @Override
    public ItemStack[] getRequiredItems() {
        return new ItemStack[] {items[id]};
    }

    @Override
    public boolean[] getRequiredItemsConsumed() {
        return new boolean[] {true};
    }

    @Override
    public String getLocalizedName() {
        return LocalizationHelper.localize("card.bore.name");
    }

    @Override
    public String getLocalizedText() {
        return LocalizationHelper.localize("card.bore.text");
    }

}
