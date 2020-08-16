package de.zpenguin.thaumicwands.util;

import de.zpenguin.thaumicwands.api.item.wand.IWandCap;
import de.zpenguin.thaumicwands.api.item.wand.IWandRod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;

public class WandWrapper {

	IWandRod rod;
	IWandCap cap;

	public WandWrapper() {
		this(null,null);
	}

	public WandWrapper(IWandRod rod, IWandCap cap) {
		this.rod = rod;
		this.cap = cap;
	}


	public boolean canCraft(EntityPlayer player) {
		return isValidWand() && ThaumcraftCapabilities.knowsResearch(player, rod.getRequiredResearch(), cap.getRequiredResearch());
	}

	public boolean isValidWand() {
		return rod!=null && cap!=null && !(rod.getTag() == "wood" && cap.getTag() == "iron");
	}

	public int getVisCost() {
		return isValidWand() ?  rod.getCraftCost() * cap.getCraftCost() : 0;
	}

	public AspectList getCrystals() {
		AspectList aspects = new AspectList();

		if(isValidWand()) {
			int cost = Math.max(rod.getCraftCost(), cap.getCraftCost());
			for(Aspect a: Aspect.getPrimalAspects())
				aspects.add(a,cost);
		}

		return aspects;

	}

	public ItemStack makeWand() {
		return isValidWand() ? WandHelper.getWandWithParts(rod.getTag(), cap.getTag()) : ItemStack.EMPTY;
	}

	public WandWrapper copy() {
		return new WandWrapper(rod, cap);
	}

	public IWandCap getCap() {
		return cap;
	}

	public IWandRod getRod() {
		return rod;
	}

	public WandWrapper setCap(IWandCap cap) {
		this.cap = cap;
		return this;
	}

	public WandWrapper setRod(IWandRod rod) {
		this.rod = rod;
		return this;
	}

	@Override
	public String toString() {
		if(rod != null && cap != null)
			return "Rod: "+rod.getTag()+" | Cap: "+cap.getTag();
		return "";
	}

}
