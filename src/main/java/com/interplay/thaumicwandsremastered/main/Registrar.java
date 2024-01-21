package com.interplay.thaumicwandsremastered.main;

import com.interplay.thaumicwandsremastered.entiy.TWR_Entities;
import com.interplay.thaumicwandsremastered.item.PolyItems;
import com.interplay.thaumicwandsremastered.item.TWR_Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod.EventBusSubscriber(modid = ThaumicWandsRemastered.MODID)
public class Registrar {

    @SubscribeEvent
    public void registerItems(Register<Item> r) {
        TWR_Items.registerItems(r);
        PolyItems.registerItems(r);
    }

    @SubscribeEvent
    public static void registerRecipes(Register<IRecipe> r) {
        TWR_Recipes.registerRecipes(r);
        PolyRecipes.registerRecipes(r);
    }

    @SubscribeEvent
    public static void registerEntities(Register<EntityEntry> r) {
        TWR_Entities.registerEntities(r);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent e){
        TWR_Items.registerRenders(e);
        PolyItems.registerRenders(e);
    }
}
