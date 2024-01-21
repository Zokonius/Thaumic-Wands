package com.interplay.thaumicwandsremastered.compat;

import com.interplay.thaumicwandsremastered.api.IWandCap;
import com.interplay.thaumicwandsremastered.api.ThaumicWandsRemasteredAPI;
import com.interplay.thaumicwandsremastered.wand.WandCap;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.thaumicwandsremastered.WandCaps")
public class WandCaps {

    @ZenMethod
    public static void register(String tag, String research, float discount, IItemStack cap, int cost) {
        WandCap wandcap = new WandCap(tag, discount, toStack(cap), cost, research);
        CraftTweakerCompat.LATE_ADDITIONS.add(new Add(wandcap));
    }

    @ZenMethod
    public static void register(String tag, float discount, IItemStack cap, int cost) {
        WandCap wandcap = new WandCap(tag, discount, toStack(cap), cost);
        CraftTweakerCompat.LATE_ADDITIONS.add(new Add(wandcap));
    }

    @ZenMethod
    public static void remove(IItemStack cap) {
        CraftTweakerCompat.LATE_REMOVALS.add(new Remove(toStack(cap)));
    }

    @ZenMethod
    public static void remove(String tag) {
        CraftTweakerCompat.LATE_REMOVALS.add(new Remove(tag));
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
            ThaumicWandsRemasteredAPI.registerWandCap(cap.getTag(), cap);

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
            for(IWandCap c : ThaumicWandsRemasteredAPI.wandCaps.values())
                if(ItemStack.areItemsEqual(cap, c.getItemStack()))
                    this.cap = c.getTag();
        }

        @Override
        public void apply() {
            ThaumicWandsRemasteredAPI.wandCaps.remove(cap);

        }

        @Override
        public String describe() {
            return "Removing Wand Cap: " + cap;
        }

    }
}
