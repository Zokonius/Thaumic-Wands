package de.zpenguin.thaumicwands.client.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import de.zpenguin.thaumicwands.container.ContainerArcaneWorkbenchNew;
import de.zpenguin.thaumicwands.crafting.ThaumicWandsCraftingManager;
import de.zpenguin.thaumicwands.main.ThaumicWands;
import de.zpenguin.thaumicwands.util.LocalizationHelper;
import de.zpenguin.thaumicwands.util.WandHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.common.blocks.world.ore.ShardType;
import thaumcraft.common.container.ContainerArcaneWorkbench;
import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;

public class GuiArcaneWorkbenchNew extends GuiContainer {

	private TileArcaneWorkbench tileEntity;
	private InventoryPlayer ip;
	ResourceLocation tex;

	public GuiArcaneWorkbenchNew(InventoryPlayer inventory, TileArcaneWorkbench e) {
		super(new ContainerArcaneWorkbenchNew(inventory, e));
		this.tex = new ResourceLocation(ThaumicWands.modID, "textures/gui/arcaneworkbench.png");
		this.tileEntity = e;
		this.ip = inventory;
		this.ySize = 234;
		this.xSize = 190;
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.renderEngine.bindTexture(this.tex);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		int cost = 0;
		int discount = 0;
		IArcaneRecipe result = ThaumicWandsCraftingManager.findMatchingArcaneRecipe(this.tileEntity.inventoryCraft, this.ip.player);
		AspectList crystals = null;
		if(result != null) {
			cost = result.getVis();
			cost = WandHelper.getActualVisCost(cost, getWand(), this.ip.player);
			discount = (int) (WandHelper.getTotalDiscount(getWand(), this.ip.player) * 100F);
			crystals = result.getCrystals();

		if(crystals != null) {
			GlStateManager.blendFunc(770, 1);
			for(Aspect a : crystals.getAspects()) {
				int id = ShardType.getMetaByAspect(a);
				Color col = new Color(a.getColor());
				GlStateManager.color(col.getRed() / 255.0F, col.getGreen() / 255.0F, col.getBlue() / 255.0F, 0.33F);
				GlStateManager.pushMatrix();
				GlStateManager.translate((var5 + ContainerArcaneWorkbench.xx[id]) + 7.5F,(var6 + ContainerArcaneWorkbench.yy[id]) + 8.0F, 0.0F);
				GlStateManager.rotate((id * 60 + (this.mc.getRenderViewEntity()).ticksExisted % 360) + mouseX, 0.0F, 0.0F, 1.0F);
				GlStateManager.scale(0.5F, 0.5F, 0.0F);
				drawTexturedModalRect(-32, -32, 192, 0, 64, 64);
				GlStateManager.scale(1.0F, 1.0F, 1.0F);
				GlStateManager.popMatrix();
			}
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.blendFunc(770, 771);
		}
		}
		GlStateManager.disableBlend();
		GlStateManager.pushMatrix();
		GlStateManager.translate((var5 + 168), (var6 + 46), 0.0F);
		GlStateManager.scale(0.5F, 0.5F, 0.0F);
		int charge = RechargeHelper.getCharge(getWand());
		String text = (charge < 0 ? "0": charge)+ " " + LocalizationHelper.localize("workbench.available");
		int ll = this.fontRenderer.getStringWidth(text) / 2;
		this.fontRenderer.drawString(text, -ll, 5, (Math.max(RechargeHelper.getCharge(getWand()),0) < cost) ? 15625838 : 7237358);
		GlStateManager.scale(1F, 1F, 1F);
		GlStateManager.popMatrix();

		if(cost > 0) {
			if(charge < cost) {
				GlStateManager.pushMatrix();
				GlStateManager.color(0.33F, 0.33F, 0.33F, 0.66F);
		        GL11.glEnable(GL11.GL_LIGHTING);
		        GL11.glEnable(GL11.GL_CULL_FACE);
		        GL11.glEnable(GL11.GL_BLEND);
				this.itemRender.renderItemAndEffectIntoGUI(result.getCraftingResult((InventoryCrafting) this.tileEntity.inventoryCraft), var5 + 160, var6 + 64);
				this.itemRender.renderItemOverlayIntoGUI(this.mc.fontRenderer, result.getCraftingResult((InventoryCrafting) this.tileEntity.inventoryCraft), var5 + 160, var6 + 64, "");
				GlStateManager.enableLighting();
				GlStateManager.enableDepth();
				RenderHelper.enableStandardItemLighting();
				GL11.glPopMatrix();
				RenderHelper.disableStandardItemLighting();
			}
			GL11.glPushMatrix();
			GlStateManager.translate((var5 + 168), (var6 + 38), 0.0F);
			GlStateManager.scale(0.5F, 0.5F, 0.0F);
			text = cost + " " + LocalizationHelper.localize("workbench.cost");
			if(discount > 0)
				text = text + " (" + discount + "% " + LocalizationHelper.localize("workbench.discount") + ")";
			ll = this.fontRenderer.getStringWidth(text) / 2;
			this.fontRenderer.drawString(text, -ll, 10, 12648447);
			GlStateManager.scale(1F, 1F, 1F);
			GlStateManager.popMatrix();
		}
	}

	private ItemStack getWand() {
		return this.tileEntity.inventoryCraft.getStackInSlot(15);
	}

}
