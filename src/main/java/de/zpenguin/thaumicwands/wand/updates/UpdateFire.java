package de.zpenguin.thaumicwands.wand.updates;

import de.zpenguin.thaumicwands.api.item.wand.IWandUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import thaumcraft.api.items.RechargeHelper;

public class UpdateFire implements IWandUpdate {

	@Override
	public void onUpdate(ItemStack stack, EntityPlayer player) {
		if(player.getEntityWorld().getTotalWorldTime() % 20 == 0) {
			Biome b = player.getEntityWorld().getBiome(player.getPosition());
		if(BiomeDictionary.getTypes(b).contains(Type.DRY) && !BiomeDictionary.getTypes(b).contains(Type.NETHER) && RechargeHelper.getChargePercentage(stack, player) < 0.2F)
				RechargeHelper.rechargeItemBlindly(stack, player, 1);
		}
	}

}
