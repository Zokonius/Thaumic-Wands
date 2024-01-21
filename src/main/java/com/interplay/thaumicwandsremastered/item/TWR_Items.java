package com.interplay.thaumicwandsremastered.item;

import com.interplay.thaumicwandsremastered.main.ThaumicWandsRemastered;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

import java.util.ArrayList;
import java.util.Objects;

public class TWR_Items {
    public static final ArrayList<Item> ITEMS = new ArrayList<>();

    public static final Item itemWand = new ItemWand("item_wand");
    public static final Item itemWandCap = new ItemBaseMeta("item_wand_cap","iron","copper","brass","silver_inert","silver","thaumium_inert","thaumium","void_inert","void");
    public static final Item itemWandRod = new ItemBaseMeta("item_wand_rod","greatwood","reed","blaze","ice","obsidian","quartz","bone","silverwood");
    public static final Item itemPrimalCharm = new ItemPrimalCharm("item_primal_charm");



    public static void registerItems(RegistryEvent.Register<Item> r) {
        for(Item item : ITEMS)
            registerItem(r, item);
    }

    public static void registerRenders(ModelRegistryEvent event) {
        for(Item item : ITEMS)
            registerRender(item);
    }

    private static void registerItem(RegistryEvent.Register<Item> r, Item item) {
        r.getRegistry().register(item);
    }

    private static void registerRender(Item item) {

        if (item instanceof ItemBaseMeta) {
            ItemBaseMeta it = (ItemBaseMeta)item;
            for(int i = 0; !(i == ((ItemBaseMeta)item).getVariants().length); i++)
                ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(ThaumicWandsRemastered.MODID + ":items/" + it.getBaseName(), it.getVariants()[i]));
        }

        else if(item instanceof ItemWand) {
            ModelLoader.setCustomMeshDefinition(item, stack -> new ModelResourceLocation("thaumicwandsremastered:item_wand_3d"));
        }

        else {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
