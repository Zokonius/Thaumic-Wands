package de.zpenguin.thaumicwands.util.research.cards;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class CardFocussing extends TheorycraftCard {

	@Override
	public int getInspirationCost() {
		return 1;
	}

	@Override
	public String getResearchCategory() {
		return "THAUMATURGY";
	}

	@Override
	public String getLocalizedName() {
		return new TextComponentTranslation("card.focussing.name", new Object[0]).getFormattedText();

	}

	@Override
	public String getLocalizedText() {
		return new TextComponentTranslation("card.focussing.text", new Object[0]).getFormattedText();

	}

	@Override
	public boolean activate(EntityPlayer player, ResearchTableData data) {
		data.addTotal(getResearchCategory(), 20);
		return true;
	}

}
