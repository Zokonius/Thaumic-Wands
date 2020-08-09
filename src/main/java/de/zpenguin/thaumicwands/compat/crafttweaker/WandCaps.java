package de.zpenguin.thaumicwands.compat.crafttweaker;

import com.blamejared.compat.thaumcraft.handlers.ThaumCraft;
import com.blamejared.compat.thaumcraft.handlers.aspects.CTAspectStack;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import de.zpenguin.thaumicwands.api.ThaumicWandsAPI;
import de.zpenguin.thaumicwands.api.item.wand.IWandCap;
import de.zpenguin.thaumicwands.wand.WandCap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional.Method;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.thaumicwands.WandCaps")
public class WandCaps {


    @Method(modid = "modtweaker")
    @ZenMethod
    public static void register(String tag, String research, float discount, CTAspectStack[] aspects, IItemStack cap, int cost) {
    	WandCap wandcap = new WandCap(tag, discount, ThaumCraft.getAspects(aspects), toStack(cap), cost, research);
    	CrafttweakerCompat.LATE_ADDITIONS.add(new Add(wandcap));
    }


    @Method(modid = "modtweaker")
    @ZenMethod
    public static void register(String tag, float discount, CTAspectStack[] aspects, IItemStack cap, int cost) {
    	WandCap wandcap = new WandCap(tag, discount, ThaumCraft.getAspects(aspects), toStack(cap), cost);
    	CrafttweakerCompat.LATE_ADDITIONS.add(new Add(wandcap));
	}

    @ZenMethod
    public static void register(String tag, String research, float discount, IItemStack cap, int cost) {
    	WandCap wandcap = new WandCap(tag, discount, toStack(cap), cost, research);
    	CrafttweakerCompat.LATE_ADDITIONS.add(new Add(wandcap));
    }

    @ZenMethod
    public static void register(String tag, float discount, IItemStack cap, int cost) {
    	WandCap wandcap = new WandCap(tag, discount, toStack(cap), cost);
    	CrafttweakerCompat.LATE_ADDITIONS.add(new Add(wandcap));
    }

    @ZenMethod
    public static void remove(IItemStack cap) {
    	CrafttweakerCompat.LATE_REMOVALS.add(new Remove(toStack(cap)));
    }

    @ZenMethod
    public static void remove(String tag) {
    	CrafttweakerCompat.LATE_REMOVALS.add(new Remove(tag));
    }

    public static ItemStack toStack(IItemStack iStack) {
        if (iStack == null)
            return ItemStack.EMPTY;

        Object internal = iStack.getInternal();
        return (ItemStack) internal;
    }

    private static class Add implements IAction {

    	IWandCap cap;

    	public Add(IWandCap cap) {
    		this.cap = cap;
		}

		@Override
		public void apply() {
			ThaumicWandsAPI.registerWandCap(cap.getTag(), cap);

		}

		@Override
		public String describe() {
			return "Adding Wand Cap: " + cap.getTag();
		}

    }

    private static class Remove implements IAction {

    	String cap;

    	public Remove(String cap) {
    		this.cap = cap;
		}

    	public Remove(ItemStack cap) {
    		for(IWandCap c : ThaumicWandsAPI.wandCaps.values())
    			if(ItemStack.areItemsEqual(cap, c.getItemStack()))
    				this.cap = c.getTag();
		}

		@Override
		public void apply() {
			ThaumicWandsAPI.wandCaps.remove(cap);

		}

		@Override
		public String describe() {
			return "Removing Wand Cap: " + cap;
		}

    }

}
