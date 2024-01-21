package com.interplay.thaumicwandsremastered.compat.bloodmagic;

import com.interplay.thaumicwandsremastered.compat.PolyCompat;
import com.interplay.thaumicwandsremastered.util.LocalizationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class CardHellfireForge extends TheorycraftCard {

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
        return new ItemStack[] {PolyCompat.getItem("bloodmagic:monster_soul", 0)};
    }

    @Override
    public boolean[] getRequiredItemsConsumed() {
        return new boolean[] {true};
    }

    @Override
    public String getLocalizedName() {
        return LocalizationHelper.localize("card.hellfireforge.name");
    }

    @Override
    public String getLocalizedText() {
        return LocalizationHelper.localize("card.hellfireforge.text");
    }

}
