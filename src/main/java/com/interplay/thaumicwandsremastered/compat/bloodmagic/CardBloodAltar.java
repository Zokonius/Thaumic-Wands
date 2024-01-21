package com.interplay.thaumicwandsremastered.compat.bloodmagic;

import com.interplay.thaumicwandsremastered.compat.PolyCompat;
import com.interplay.thaumicwandsremastered.util.LocalizationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class CardBloodAltar extends TheorycraftCard {

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
        return new ItemStack[] {PolyCompat.getItem("bloodmagic:slate", 0, 4)};
    }

    @Override
    public boolean[] getRequiredItemsConsumed() {
        return new boolean[] {true};
    }

    @Override
    public String getLocalizedName() {
        return LocalizationHelper.localize("card.bloodaltar.name");
    }

    @Override
    public String getLocalizedText() {
        return LocalizationHelper.localize("card.bloodaltar.text");
    }

}
