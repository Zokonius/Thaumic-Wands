package com.interplay.thaumicwandsremastered.wand;

import com.interplay.thaumicwandsremastered.api.IWandCap;
import com.interplay.thaumicwandsremastered.api.IWandRod;
import com.interplay.thaumicwandsremastered.api.ThaumicWandsRemasteredAPI;
import com.interplay.thaumicwandsremastered.item.TWR_Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import java.util.ArrayList;

public class TWR_Wands {
    public static final ArrayList<IWandCap> CAPS = new ArrayList<IWandCap>();
    public static final ArrayList<IWandRod> RODS = new ArrayList<IWandRod>();

    public static final IWandCap capIron = new WandCap("iron", 1F, new ItemStack(TWR_Items.itemWandCap, 1, 0), 1, "UNLOCKTHAUMATURGY");
    public static final IWandCap capCopper = new WandCap("copper", 1F, new AspectList().add(Aspect.ORDER,1).add(Aspect.ENTROPY,1),new ItemStack(TWR_Items.itemWandCap, 1, 1), 5);
    public static final IWandCap capBrass = new WandCap("brass", 0.95F, new ItemStack(TWR_Items.itemWandCap, 1, 2), 10);
    public static final IWandCap capSilver = new WandCap("silver", 0.95F, new AspectList().add(Aspect.AIR,1).add(Aspect.FIRE,1).add(Aspect.WATER,1).add(Aspect.EARTH,1),new ItemStack(TWR_Items.itemWandCap, 1, 4), 15);
    public static final IWandCap capThaumium = new WandCap("thaumium", 0.9F, new ItemStack(TWR_Items.itemWandCap, 1, 6), 20);
    public static final IWandCap capVoid = new WandCap("void", 0.8F, new ItemStack(TWR_Items.itemWandCap,1,8), 25);

    public static final IWandRod rodWood = new WandRod("wood", 50, new ItemStack(Items.STICK), 1, "UNLOCKTHAUMATURGY");
    public static final IWandRod rodGreatwood = new WandRod("greatwood", 200, new ItemStack(TWR_Items.itemWandRod, 1, 0), 5);
    public static final IWandRod rodReed = new WandRod("reed", 600, new ItemStack(TWR_Items.itemWandRod, 1, 1), 10, new UpdateAir());
    public static final IWandRod rodBlaze = new WandRod("blaze", 600, new ItemStack(TWR_Items.itemWandRod, 1, 2), 10, new UpdateFire());
    public static final IWandRod rodIce = new WandRod("ice", 600, new ItemStack(TWR_Items.itemWandRod, 1, 3), 10, new UpdateWater());
    public static final IWandRod rodObsidian = new WandRod("obsidian", 600, new ItemStack(TWR_Items.itemWandRod, 1, 4), 10, new UpdateEarth());
    public static final IWandRod rodQuartz = new WandRod("quartz", 600, new ItemStack(TWR_Items.itemWandRod, 1, 5), 10);
    public static final IWandRod rodBone = new WandRod("bone", 600, new ItemStack(TWR_Items.itemWandRod, 1, 6), 10);
    public static final IWandRod rodSilverwood = new WandRod("silverwood", 800, new ItemStack(TWR_Items.itemWandRod, 1, 7), 15);

    public static void registerWandParts() {
        for(IWandCap cap: CAPS)
            ThaumicWandsRemasteredAPI.registerWandCap(cap.getTag(), cap);
        for(IWandRod rod: RODS)
            ThaumicWandsRemasteredAPI.registerWandRod(rod.getTag(), rod);
    }
}
