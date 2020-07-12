package de.zpenguin.thaumicwands.util;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import thaumcraft.api.aspects.Aspect;

public class LocalizationHelper {

	public static String localize(String name) {
		return new TextComponentTranslation(name, new Object[0]).getFormattedText();
	}

	public static TextFormatting getTextColorFromAspect(Aspect a) {
		if(a.isPrimal())
			switch(a.getChatcolor()) {
			case "e": return TextFormatting.YELLOW;
			case "c": return TextFormatting.RED;
			case "3": return TextFormatting.DARK_AQUA;
			case "2": return TextFormatting.DARK_GREEN;
			case "7": return TextFormatting.GRAY;
			case "8": return TextFormatting.DARK_GRAY;
			}

		return TextFormatting.RESET;
	}

}
