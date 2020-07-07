package de.zpenguin.thaumicwands.main;

import de.zpenguin.thaumicwands.entity.TW_Entities;
import de.zpenguin.thaumicwands.item.TW_Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@EventBusSubscriber(modid = ThaumicWands.modID)
public class Registrar {

	@SubscribeEvent
	public static void registerItems(Register<Item> r) {
		TW_Items.registerItems(r);
	}

	@SubscribeEvent
	public static void registerRecipes(Register<IRecipe> r) {
		TW_Recipes.registerRecipes(r);
	}

	@SubscribeEvent
	public static void registerEntities(Register<EntityEntry> r) {
		TW_Entities.registerEntities(r);
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent e) {
		TW_Items.registerRenders(e);
	}

}
