package de.zpenguin.thaumicwands.client.model;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import de.zpenguin.thaumicwands.item.ItemWand;
import de.zpenguin.thaumicwands.main.ThaumicWands;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModelWand extends ModelBase {

	final ModelRenderer rod;
	final ModelRenderer capTop;
	final ModelRenderer capBottom;
	final ModelRenderer focus;

	public ModelWand() {
		textureWidth = 32;
		textureHeight = 32;

		rod = new ModelRenderer(this, 0, 8);
		rod.addBox(-1F, 0F, -1F, 2, 18, 2);
		rod.setRotationPoint(0, 2, 0);
		rod.setTextureSize(64, 64);

		capTop = new ModelRenderer(this, 0, 0);
		capTop.addBox(-1F, -1F, -1F, 2, 2, 2);
		capTop.setRotationPoint(0, 2, 0);
		capTop.setTextureSize(64, 32);

		capBottom = new ModelRenderer(this, 0, 0);
		capBottom.addBox(-1F, -1F, -1F, 2, 2, 2);
		capBottom.setRotationPoint(0, 20, 0);
		capBottom.setTextureSize(64, 32);

		focus = new ModelRenderer(this, 0, 0);
		focus.addBox(-1, -1, -1, 2, 2, 2);
		focus.setRotationPoint(0, 22, 0);
		focus.setTextureSize(32, 32);

	}

	public void render(ItemStack wandStack) {
		float scale = 1F / 16F;
		ItemWand wand = (ItemWand) wandStack.getItem();
		Minecraft.getMinecraft().renderEngine.bindTexture(wand.getRod(wandStack).getTexture());
		GL11.glPushMatrix();
		GlStateManager.translate(0.66375, -0.125, 0.66375);
		rod.render(scale);
		Minecraft.getMinecraft().renderEngine.bindTexture(wand.getCap(wandStack).getTexture());
		GL11.glScaled(1.275D, 1.0D, 1.275D);
		GlStateManager.enableLighting();
		capTop.render(scale);
		capBottom.render(scale);

		if(wand.getFocus(wandStack)!=null) {
		Color c = new Color(wand.getFocus(wandStack).getFocusColor(wand.getFocusStack(wandStack)));
		GL11.glColor3f(c.getRed()/255F, c.getGreen()/255F, c.getBlue()/255F);
		GlStateManager.scale(1.1, 1.1, 1.1);
		GlStateManager.translate(0, -0.125, 0);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ThaumicWands.modID, "textures/models/wand_focus.png"));
		focus.render(scale);
		GL11.glColor3f(1F,1F,1F);
		}
		GL11.glPopMatrix();
	}

}
