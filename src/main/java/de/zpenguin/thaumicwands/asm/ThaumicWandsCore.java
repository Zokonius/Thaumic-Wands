package de.zpenguin.thaumicwands.asm;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name(value = "ThaumicWandsCore")
@IFMLLoadingPlugin.TransformerExclusions({"de.zpenguin.thaumicwands.asm"})
@IFMLLoadingPlugin.SortingIndex(1006)
public class ThaumicWandsCore implements IFMLLoadingPlugin {

    public ThaumicWandsCore() {

	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{"de.zpenguin.thaumicwands.asm.WandTransformer"};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

	@Override
	public String getAccessTransformerClass() {
		return "de.zpenguin.thaumicwands.asm.WandTransformer";
	}

}