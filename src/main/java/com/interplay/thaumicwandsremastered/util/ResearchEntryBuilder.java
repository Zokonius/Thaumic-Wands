package com.interplay.thaumicwandsremastered.util;

import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.common.lib.research.ResearchManager;

import java.lang.reflect.Method;

public class ResearchEntryBuilder {
    private ResearchEntry entry = new ResearchEntry();

    public static ResearchEntryBuilder start() {
        return new ResearchEntryBuilder();
    }

    public ResearchEntryBuilder setBaseInfo(String key, String category, int x, int y, Object... icons) {
        return setKey(key).setCategory(category).setName(key).setPosition(x, y).setIcons(icons);
    }

    public ResearchEntryBuilder setKey(String key) {
        entry.setKey(key);
        return this;
    }

    public ResearchEntryBuilder setCategory(String category) {
        entry.setCategory(category);
        return this;
    }

    public ResearchEntryBuilder setPosition(int x, int y) {
        entry.setDisplayColumn(x);
        entry.setDisplayRow(y);
        return this;
    }

    public ResearchEntryBuilder setIcons(Object... icons) {
        entry.setIcons(icons);
        return this;
    }

    public ResearchEntryBuilder setMeta(ResearchEntry.EnumResearchMeta... metas) {
        entry.setMeta(metas);
        return this;
    }

    public ResearchEntryBuilder setAddenda(ResearchAddendum... addenda) {
        entry.setAddenda(addenda);
        return this;
    }

    public ResearchEntryBuilder setName(String name) {
        entry.setName("research." + name + ".title");
        return this;
    }

    public ResearchEntryBuilder setParents(String... parents) {
        entry.setParents(parents);
        return this;
    }

    public ResearchEntryBuilder setRewardItems(ItemStack... rewards) {
        entry.setRewardItem(rewards);
        return this;
    }

    public ResearchEntryBuilder setRewardKnow(ResearchStage.Knowledge... rewardKnow) {
        entry.setRewardKnow(rewardKnow);
        return this;
    }

    public ResearchEntryBuilder setSiblings(String... siblings) {
        entry.setSiblings(siblings);
        return this;
    }

    public ResearchEntryBuilder setStages(ResearchStage... stages) {
        entry.setStages(stages);
        return this;
    }

    public ResearchEntry buildAndRegister() {
        if (entry == null)
            throw new IllegalStateException("Already built!");
        ResearchEntry re = entry;
        entry = null;
        addResearchToCategory(re);
        return re;
    }

    private static Method addResearchToCategory = null;
    private static final Logger LOGGER = LogManager.getLogger(ResearchEntryBuilder.class);

    private static void addResearchToCategory(ResearchEntry ri) {
        if(addResearchToCategory == null)
            try {
                addResearchToCategory = ResearchManager.class.getDeclaredMethod("addResearchToCategory", ResearchEntry.class);
                addResearchToCategory.setAccessible(true);
            } catch (Exception e) {
                LOGGER.error("An error occurred when trying to add research category", e);
            }

        try {
            addResearchToCategory.invoke(null, ri);
        } catch (Exception e) {
            LOGGER.error("An error occurred when trying to invoke a research category", e);
        }
    }
}
