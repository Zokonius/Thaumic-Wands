package de.zpenguin.thaumicwands.wand.updates;

import de.zpenguin.thaumicwands.api.item.wand.IWandUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.items.RechargeHelper;

public class UpdateAir implements IWandUpdate {

	@Override
	public void onUpdate(ItemStack stack, EntityPlayer player) {
		if(player.getEntityWorld().getTotalWorldTime() % 20 == 0)
		if(player.getPosition().getY()>=100 && RechargeHelper.getChargePercentage(stack, player) < 0.2F)
				RechargeHelper.rechargeItemBlindly(stack, player, 1);
	}

}
