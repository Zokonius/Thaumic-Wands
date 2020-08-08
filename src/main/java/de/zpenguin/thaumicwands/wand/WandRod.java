package de.zpenguin.thaumicwands.wand;

import de.zpenguin.thaumicwands.api.item.wand.IWandRod;
import de.zpenguin.thaumicwands.api.item.wand.IWandUpdate;
import de.zpenguin.thaumicwands.main.ThaumicWands;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class WandRod implements IWandRod {

	int craftCost;
	int capacity;

	String tag;
	String research;
	ItemStack item;
	IWandUpdate update;


	public WandRod(String tag, int capacity, ItemStack item, int craftCost) {
		this(tag, capacity, item, craftCost, null, "ROD_" + tag.toUpperCase());
	}

	public WandRod(String tag, int capacity, ItemStack item, int craftCost, IWandUpdate update) {
		this(tag, capacity, item, craftCost, update, "ROD_" + tag.toUpperCase());
	}

	public WandRod(String tag, int capacity, ItemStack item, int craftCost, String research) {
		this(tag, capacity, item, craftCost, null, research);
	}

	public WandRod(String tag, int capacity, ItemStack item, int craftCost, IWandUpdate update, String research) {
		this.tag = tag;
		this.capacity = capacity;
		this.item = item;
		this.craftCost = craftCost;
		this.update = update;
		this.research = research;

		TW_Wands.RODS.add(this);
	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(ThaumicWands.modID, "textures/models/wand_rod_" + tag + ".png");
	}

	@Override
	public ItemStack getItemStack() {
		return item;
	}

	@Override
	public String getTag() {
		return tag;
	}

	@Override
	public String getRequiredResearch() {
		return research;
	}

	@Override
	public int getCraftCost() {
		return craftCost;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public IWandUpdate getUpdate() {
		return update;
	}

}
