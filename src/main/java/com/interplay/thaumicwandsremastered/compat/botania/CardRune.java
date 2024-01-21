package com.interplay.thaumicwandsremastered.compat.botania;

import com.interplay.thaumicwandsremastered.compat.PolyCompat;
import com.interplay.thaumicwandsremastered.util.LocalizationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class CardRune extends TheorycraftCard {

    private int meta;

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setInteger("meta", meta);
        return nbt;

    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        this.meta = nbt.getInteger("meta");
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        meta = player.getEntityWorld().rand.nextInt(9);
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
        data.addTotal(getResearchCategory(), 25);
        return true;
    }

    @Override
    public int getInspirationCost() {
        return 1;
    }

    @Override
    public ItemStack[] getRequiredItems() {
        return new ItemStack[] {PolyCompat.getItem("botania:rune", meta)};
    }

    @Override
    public String getLocalizedName() {
        return LocalizationHelper.localize("card.rune.name");
    }

    @Override
    public String getLocalizedText() {
        return LocalizationHelper.localize("card.rune.text");
    }

}
