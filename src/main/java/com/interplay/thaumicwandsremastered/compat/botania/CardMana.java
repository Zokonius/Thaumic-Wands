package com.interplay.thaumicwandsremastered.compat.botania;

import com.interplay.thaumicwandsremastered.compat.PolyCompat;
import com.interplay.thaumicwandsremastered.util.LocalizationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class CardMana extends TheorycraftCard {

    private static final int[] meta = {0,1,2,16,22};

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
        id = player.getEntityWorld().rand.nextInt(meta.length);
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
        return new ItemStack[] {PolyCompat.getItem("botania:manaresource", meta[id])};
    }

    @Override
    public boolean[] getRequiredItemsConsumed() {
        return new boolean[] {true};
    }

    @Override
    public String getLocalizedName() {
        return LocalizationHelper.localize("card.mana.name");
    }

    @Override
    public String getLocalizedText() {
        return LocalizationHelper.localize("card.mana.text");
    }

}
