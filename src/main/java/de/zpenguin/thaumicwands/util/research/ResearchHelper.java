package de.zpenguin.thaumicwands.util.research;

import net.minecraft.block.Block;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchEntry.EnumResearchMeta;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.api.research.theorycraft.TheorycraftManager;

public class ResearchHelper {

	private static class REB extends ResearchEntryBuilder {}

	public static class RSB extends ResearchStageBuilder {}

	public static class RAB extends ResearchAddendumBuilder {}

	public static void makeResearch(String tag, String tab, int Xpos, int Ypos, Object icon, ResearchStage[] stages, String[] parents, ResearchAddendum[] add, EnumResearchMeta... meta) {
		ResearchEntryBuilder reb = new REB().setBaseInfo(tag, tab, Xpos, Ypos, icon);
		reb.setStages(stages);
		reb.setParents(parents);
		reb.setMeta(meta);
		if(add != null) reb.setAddenda(add);
		reb.buildAndRegister();
	}

	public static void makeResearch(String tag, String tab, int Xpos, int Ypos, Object icon, ResearchStage[] stages, String[] parents, EnumResearchMeta... meta) {
		makeResearch(tag, tab, Xpos, Ypos, icon, stages, parents, null, meta);
	}

	public static void makeThaumaturgyResearch(String tag, int Xpos, int Ypos, Object icon, ResearchStage[] stages, String[] parents, EnumResearchMeta... meta) {
		makeResearch(tag, "THAUMATURGY", Xpos, Ypos, icon, stages, parents, null, meta);
	}

	public static void makeThaumaturgyResearch(String tag, int Xpos, int Ypos, Object icon, ResearchStage[] stages, String[] parents, ResearchAddendum[] add, EnumResearchMeta... meta) {
		makeResearch(tag, "THAUMATURGY", Xpos, Ypos, icon, stages, parents, add, meta);
	}

	public static void makeAid(Block block, Class<? extends TheorycraftCard>... cards) {
		if(TheorycraftManager.aids.get(block.getUnlocalizedName()) == null)
			TheorycraftManager.aids.put(block.getUnlocalizedName(), new AidBase(block, cards));
	}

	public static void registerCards(Class<? extends TheorycraftCard>... cards) {
		for(Class<? extends TheorycraftCard> card : cards)
			TheorycraftManager.registerCard(card);
	}

}
