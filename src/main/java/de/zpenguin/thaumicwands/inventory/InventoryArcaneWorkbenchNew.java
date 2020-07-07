package de.zpenguin.thaumicwands.inventory;

import java.lang.reflect.Field;

import de.zpenguin.thaumicwands.asm.WandTransformer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import thaumcraft.common.container.InventoryArcaneWorkbench;

public class InventoryArcaneWorkbenchNew extends InventoryArcaneWorkbench {

	public InventoryArcaneWorkbenchNew(TileEntity tileEntity, Container container) {
		super(tileEntity, container);
		extendContainer();
	}

	public void extendContainer() {
		try {
			Field f = InventoryCrafting.class.getDeclaredField(WandTransformer.isDeobfEnvironment ? "stackList" : "field_70466_a");
			f.setAccessible(true);
			NonNullList<ItemStack> list = (NonNullList<ItemStack>) f.get(this);
			NonNullList<ItemStack> newList = NonNullList.create();
			for(ItemStack s: list)
				newList.add(s);
				newList.add(ItemStack.EMPTY);
			f.set(this, newList);
		}

		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
