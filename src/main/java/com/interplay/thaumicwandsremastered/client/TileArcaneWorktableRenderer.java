package com.interplay.thaumicwandsremastered.client;

import com.interplay.thaumicwandsremastered.tile.TileArcaneWorkbenchNew;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class TileArcaneWorktableRenderer extends TileEntitySpecialRenderer<TileArcaneWorkbenchNew> {

    static ModelWand model = new ModelWand();

    @Override
    public void render(TileArcaneWorkbenchNew te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);
        ItemStack wand = te.inventoryCraft.getStackInSlot(15);
        if(wand.isEmpty()) return;

        GlStateManager.pushMatrix();
        GlStateManager.translate(x+0.375, y+1.475, z);
        GlStateManager.scale(0.625, 0.625, 0.625);

        GlStateManager.rotate(90F, 1, 0, 0);
        GlStateManager.rotate(45F, 0, 0, 1);

        model.render(wand);
        GlStateManager.popMatrix();
    }

}
