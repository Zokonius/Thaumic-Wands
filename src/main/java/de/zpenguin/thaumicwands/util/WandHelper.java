package de.zpenguin.thaumicwands.util;

import de.zpenguin.thaumicwands.api.ThaumicWandsAPI;
import de.zpenguin.thaumicwands.api.item.wand.IWand;
import de.zpenguin.thaumicwands.item.ItemWand;
import de.zpenguin.thaumicwands.item.TW_Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.common.items.casters.CasterManager;

public class WandHelper {

	public static int getActualVisCost(int vis, ItemStack wand, EntityPlayer player) {
		return (int) (vis * getTotalDiscount(wand, player));
	}

	public static float getTotalDiscount(ItemStack wand, EntityPlayer player) {
		float discount = getWandDiscount(wand);
		return discount - CasterManager.getTotalVisDiscount(player);
	}

	public static float getWandDiscount(ItemStack wand) {
		return ((ItemWand) TW_Items.itemWand).getCap(wand).getDiscount();
	}

	public static AspectList getActualCrystals(AspectList list, ItemStack wand) {
		if(list == null) return null;
		AspectList l = new AspectList();
		AspectList subtract = ((ItemWand) TW_Items.itemWand).getCap(wand).getAspectDiscount();
		for(Aspect a:list.getAspects()) {
			if(list.getAmount(a)-subtract.getAmount(a)>0)
			l.add(a, list.getAmount(a)-subtract.getAmount(a));
		}
		return l;
	}

	public static ItemStack getWandWithParts(String rod, String cap) {
		NBTTagCompound nbt = new NBTTagCompound();
		if(ThaumicWandsAPI.getWandRod(rod) == null)
			rod = "wood";
		if(ThaumicWandsAPI.getWandCap(cap) == null)
			rod = "iron";

		nbt.setString("rod", rod);
		nbt.setString("cap", cap);

		ItemStack is = new ItemStack(TW_Items.itemWand);
		is.setTagCompound(nbt);
		return is;
	}

	public static ItemStack isWandInHotbarWithRoom(EntityPlayer player, int amount) {
		for(int i=0;i!=InventoryPlayer.getHotbarSize();i++) {
			ItemStack wand = player.inventory.mainInventory.get(i);
			if(wand.getItem() instanceof IWand) {
				if(RechargeHelper.getCharge(wand) < ((ItemWand)TW_Items.itemWand).getMaxCharge(wand, null)) {
					return wand;
			}}
		}

		ItemStack wand = player.inventory.offHandInventory.get(0);
		if(wand.getItem() instanceof IWand)
			if(RechargeHelper.getCharge(wand) + amount >= ((ItemWand)TW_Items.itemWand).getMaxCharge(wand, null))
				return wand;

		return ItemStack.EMPTY;

	}

	public static ItemStack getHeldWand(EntityPlayer player) {
		if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof IWand)
			return player.getHeldItemMainhand();
		if(player.getHeldItemOffhand()!=null && player.getHeldItemOffhand().getItem() instanceof IWand)
			return player.getHeldItemOffhand();
		return ItemStack.EMPTY;
	}

}
