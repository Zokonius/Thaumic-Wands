package de.zpenguin.thaumicwands.api.item.wand;

import de.zpenguin.thaumicwands.api.item.IFractionalVis;
import net.minecraft.item.ItemStack;
import thaumcraft.api.casters.ICaster;
import thaumcraft.api.items.IArchitect;

public interface IWand extends ICaster, IArchitect, IFractionalVis {

	public IWandCap getCap(ItemStack stack);

	public IWandRod getRod(ItemStack stack);

}
