package com.interplay.thaumicwandsremastered.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.Name(value = "ThaumicWandsRemasteredCore")
@IFMLLoadingPlugin.TransformerExclusions({"com.interplay.thaumicwandsremastered.asm"})
@IFMLLoadingPlugin.SortingIndex(1006)
public class ThaumicWandsRemasteredCore implements IFMLLoadingPlugin {

    public ThaumicWandsRemasteredCore() {

    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"com.interplay.thaumicwandsremastered.asm.WandTransformer"};
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
        return "com.interplay.thaumicwandsremastered.asm.WandTransformer";
    }

}
