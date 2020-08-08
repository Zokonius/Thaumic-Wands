package de.zpenguin.thaumicwands.compat.crafttweaker;

import com.blamejared.ModTweaker;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import de.zpenguin.thaumicwands.api.ThaumicWandsAPI;
import de.zpenguin.thaumicwands.api.item.wand.IWandRod;
import de.zpenguin.thaumicwands.wand.WandRod;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.thaumicwands.WandRods")
public class WandRods {

	@ZenMethod
	public static void register(String tag, int capacity, IItemStack rod, int cost) {
		WandRod wandrod = new WandRod(tag, capacity, toStack(rod), cost);
		ModTweaker.LATE_ADDITIONS.add(new Add(wandrod));
	}

	@ZenMethod
	public static void register(String tag, String research, int capacity, IItemStack rod, int cost) {
		WandRod wandrod = new WandRod(tag, capacity, toStack(rod), cost, research);
		ModTweaker.LATE_ADDITIONS.add(new Add(wandrod));
	}

	@ZenMethod
	public static void remove(IItemStack rod) {
		ModTweaker.LATE_REMOVALS.add(new Remove(toStack(rod)));
	}

	@ZenMethod
	public static void remove(String tag) {
		ModTweaker.LATE_REMOVALS.add(new Remove(tag));
	}

	public static ItemStack toStack(IItemStack iStack) {
		if(iStack == null)
			return ItemStack.EMPTY;

		Object internal = iStack.getInternal();
		return (ItemStack) internal;
	}

	private static class Add implements IAction {

		IWandRod rod;

		public Add(IWandRod rod) {
			this.rod = rod;
		}

		@Override
		public void apply() {
			ThaumicWandsAPI.registerWandRod(rod.getTag(), rod);
		}

		@Override
		public String describe() {
			return "Adding Wand Rod: " + rod.getTag();
		}

	}

	private static class Remove implements IAction {

		String rod;

		public Remove(String rod) {
			this.rod = rod;
		}

		public Remove(ItemStack rod) {
			for(IWandRod r : ThaumicWandsAPI.wandRods.values())
				if(ItemStack.areItemsEqual(rod, r.getItemStack()))
					this.rod = r.getTag();
		}

		@Override
		public void apply() {
			ThaumicWandsAPI.wandRods.remove(rod);
		}

		@Override
		public String describe() {
			return "Removing Wand Rod: " + rod;
		}

	}

}
