package com.interplay.thaumicwandsremastered.wand;

import com.interplay.thaumicwandsremastered.api.IWandUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.items.RechargeHelper;

public class UpdateEarth implements IWandUpdate {

    @Override
    public void onUpdate(ItemStack stack, EntityPlayer player) {
        if(player.getEntityWorld().getTotalWorldTime() % 20 == 0)
            if(player.getPosition().getY()<=30 && RechargeHelper.getChargePercentage(stack, player) < 0.2F)
                RechargeHelper.rechargeItemBlindly(stack, player, 1);
    }

}
