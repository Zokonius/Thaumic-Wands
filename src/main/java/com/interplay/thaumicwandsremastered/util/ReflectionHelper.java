package com.interplay.thaumicwandsremastered.util;

import com.interplay.thaumicwandsremastered.asm.WandTransformer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

public class ReflectionHelper {

    public static final Logger LOGGER = LogManager.getLogger(ReflectionHelper.class);
    public static void setInventoryEventHandler(InventoryCrafting inv, Container c) {
        try {
            Field f = InventoryCrafting.class.getDeclaredField(WandTransformer.isDeobfEnvironment ? "eventHandler": "field_70465_c");
            f.setAccessible(true);
            f.set(inv, c);

        } catch(Exception ex) {
            LOGGER.error("An error occurred when trying to set Inventory Event Handler", ex);
        }
    }

    public static Container getInventoryEventHandler(InventoryCrafting inv) {
        try {
            Field f = InventoryCrafting.class.getDeclaredField(WandTransformer.isDeobfEnvironment ? "eventHandler": "field_70465_c");
            f.setAccessible(true);
            return (Container) f.get(inv);

        } catch(Exception ex) {
            LOGGER.error("An error occurred when trying to get Inventory Event Handler", ex);
        }
        return null;
    }
}
