package de.zpenguin.thaumicwands.util.research.cards;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class CardConductance extends TheorycraftCard {

	public static final ArrayList<ItemStack> nuggets = Lists.newArrayList(new ItemStack(Items.IRON_NUGGET),new ItemStack(Items.GOLD_NUGGET),new ItemStack(ItemsTC.nuggets,1,6),new ItemStack(ItemsTC.nuggets,1,8));

	private int nuggetIndex;

	@Override
	public NBTTagCompound serialize() {
		NBTTagCompound nbt = super.serialize();
		nbt.setInteger("nuggetIndex", nuggetIndex);
		return nbt;

	}

	@Override
	public void deserialize(NBTTagCompound nbt) {
		super.deserialize(nbt);
		this.nuggetIndex = nbt.getInteger("nuggetIndex");
	}

	@Override
	public boolean initialize(EntityPlayer player, ResearchTableData data) {
		nuggetIndex = player.getEntityWorld().rand.nextInt(nuggets.size());
		return true;
	}

	@Override
	public String getResearchCategory() {
		return "THAUMATURGY";
	}

	@Override
	public boolean activate(EntityPlayer player, ResearchTableData data) {
		data.addTotal(getResearchCategory(), 25);
		return true;
	}

	@Override
	public int getInspirationCost() {
		return 1;
	}

	@Override
	public ItemStack[] getRequiredItems() {
		return new ItemStack[] {nuggets.get(nuggetIndex)};
	}

	@Override
	public String getLocalizedName() {
		return new TextComponentTranslation("card.conductance.name", new Object[0]).getFormattedText();
	}

	@Override
	public String getLocalizedText() {
		return new TextComponentTranslation("card.conductance.text", new Object[0]).getFormattedText();

	}

}
