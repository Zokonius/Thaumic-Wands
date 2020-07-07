package de.zpenguin.thaumicwands.util;

import java.lang.reflect.Field;

import de.zpenguin.thaumicwands.asm.WandTransformer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

public class ReflectionHelper {

	public static void setInventoryEventHandler(InventoryCrafting inv, Container c) {
		try {
			Field f = InventoryCrafting.class.getDeclaredField(WandTransformer.isDeobfEnvironment ? "eventHandler": "field_70465_c");
			f.setAccessible(true);
			f.set(inv, c);

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Container getInventoryEventHandler(InventoryCrafting inv) {
		try {
			Field f = InventoryCrafting.class.getDeclaredField(WandTransformer.isDeobfEnvironment ? "eventHandler": "field_70465_c");
			f.setAccessible(true);
			return (Container) f.get(inv);

		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
