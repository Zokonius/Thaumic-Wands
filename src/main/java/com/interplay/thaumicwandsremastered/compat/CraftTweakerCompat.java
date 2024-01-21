package com.interplay.thaumicwandsremastered.compat;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class CraftTweakerCompat implements TWR_Compat.ICompat {
    public static final List<IAction> LATE_REMOVALS = new LinkedList<>();
    public static final List<IAction> LATE_ADDITIONS = new LinkedList<>();
    public static final Logger LOGGER = LogManager.getLogger(CraftTweakerCompat.class);

    public void loadComplete(FMLLoadCompleteEvent e) {
        try {
            LATE_REMOVALS.forEach(CraftTweakerAPI::apply);
            LATE_ADDITIONS.forEach(CraftTweakerAPI::apply);
        } catch(Exception ex) {
            LOGGER.error("An error occurred when trying to load complete event", ex);
            CraftTweakerAPI.logError("Error while applying actions", ex);
        }
    }

    @Override
    public void init() {}

    @Override
    public void initRecipes() {}

    @Override
    public void initResearch() {}
}
