package com.interplay.thaumicwandsremastered.wand.polymancywands;

import com.interplay.thaumicwandsremastered.api.IWandCap;
import com.interplay.thaumicwandsremastered.api.IWandRod;
import com.interplay.thaumicwandsremastered.api.ThaumicWandsRemasteredAPI;
import com.interplay.thaumicwandsremastered.item.PolyItems;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import java.util.ArrayList;

public class PolyWands {
    public static ArrayList<IWandCap> caps = new ArrayList<>();
    public static ArrayList<IWandRod> rods = new ArrayList<>();

    public static IWandCap capManasteel = new PolyWandCap("manasteel", 0.9F, new ItemStack(PolyItems.itemWandCap, 1, 1), 20);
    public static IWandCap capElementium = new PolyWandCap("elementium", 0.85F, new AspectList().add(Aspect.AIR,1).add(Aspect.FIRE,1).add(Aspect.WATER,1).add(Aspect.EARTH,1), new ItemStack(PolyItems.itemWandCap, 1, 3), 25);
    public static IWandCap capTerrasteel = new PolyWandCap("terrasteel", 1.2F, new AspectList().add(Aspect.EARTH, 5), new ItemStack(PolyItems.itemWandCap, 1, 4), 1);
    public static IWandCap capDawnstone = new PolyWandCap("dawnstone", 0.95F, new AspectList().add(Aspect.FIRE,1).add(Aspect.AIR,1), new ItemStack(PolyItems.itemWandCap, 1, 6), 10);
    public static IWandCap capAlchemical = new PolyWandCap("alchemical", 0.9F,new AspectList().add(Aspect.WATER,1), new ItemStack(PolyItems.itemWandCap, 1, 7), 20);
    public static IWandCap capStarmetal = new PolyWandCap("starmetal", 0.9F, new ItemStack(PolyItems.itemWandCap, 1, 9), 20);

    public static IWandRod rodInfernal = new PolyWandRod("infernal", 1200, new ItemStack(PolyItems.itemWandRod, 1, 0), 20, String.valueOf(new InfernalUpdate()));
    public static IWandRod rodChorus = new PolyWandRod("chorus", 1200, new ItemStack(PolyItems.itemWandRod, 1, 1), 20, String.valueOf(new ChorusUpdate()));
    public static IWandRod rodLivingwood = new PolyWandRod("livingwood", 600, new ItemStack(PolyItems.itemWandRod, 1, 3),15, String.valueOf(new ManaUpdate()));
    public static IWandRod rodDreamwood = new PolyWandRod("dreamwood", 800, new ItemStack(PolyItems.itemWandRod, 1, 5), 15, String.valueOf(new ManaUpdate()));
    public static IWandRod rodBlood = new PolyWandRod("blood", 800, new ItemStack(PolyItems.itemWandRod, 1, 9), 15, String.valueOf(new BloodUpdate()));
    public static IWandRod rodEquivalent = new PolyWandRod("equivalent", 800, new ItemStack(PolyItems.itemWandRod,1, 10), 25, String.valueOf(new EMCUpdate()));

    public static void registerWandParts() {
        for(IWandCap cap: caps)
            ThaumicWandsRemasteredAPI.registerWandCap(cap.getTag(), cap);
        for(IWandRod rod: rods)
            ThaumicWandsRemasteredAPI.registerWandRod(rod.getTag(), rod);
    }
}
