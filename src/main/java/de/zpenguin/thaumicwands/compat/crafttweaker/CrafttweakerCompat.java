package de.zpenguin.thaumicwands.compat.crafttweaker;

import java.util.LinkedList;
import java.util.List;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import de.zpenguin.thaumicwands.compat.TW_Compat.ICompat;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;

public class CrafttweakerCompat implements ICompat {

    public static final List<IAction> LATE_REMOVALS = new LinkedList<>();
    public static final List<IAction> LATE_ADDITIONS = new LinkedList<>();

    public void loadComplete(FMLLoadCompleteEvent e) {
        try {
            LATE_REMOVALS.forEach(CraftTweakerAPI::apply);
            LATE_ADDITIONS.forEach(CraftTweakerAPI::apply);
        } catch(Exception ex) {
            ex.printStackTrace();
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
