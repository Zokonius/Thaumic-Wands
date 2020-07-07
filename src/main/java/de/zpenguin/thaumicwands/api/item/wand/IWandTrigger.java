package de.zpenguin.thaumicwands.api.item.wand;

import thaumcraft.api.crafting.IDustTrigger;

public interface IWandTrigger extends IDustTrigger {

	public default int getCost() {
		return 0;
	}

}
