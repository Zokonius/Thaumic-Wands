package de.zpenguin.thaumicwands.client.render.entity;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import de.zpenguin.thaumicwands.entity.EntityVisOrb;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class EntityVisOrbRenderer extends Render<EntityVisOrb> {

	public EntityVisOrbRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityVisOrb orb, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.enableBlend();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("thaumicwands", "textures/misc/particles.png"));
	    int i = (int)(System.nanoTime() / 25000000L % 16L);
	    Tessellator tes = Tessellator.getInstance();

	    float f2 = i / 16.0F;
	    float f3 = (i + 1) / 16.0F;
	    float f4 = 0.5F;
	    float f5 = 0.5625F;
	    float f6 = 1.0F;
	    float f7 = 0.5F;
	    float f8 = 0.25F;
	    int j = orb.getBrightnessForRender();
	    int k = j % 65536;
	    int l = j / 65536;

	    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k / 1.0F, l / 1.0F);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
	    GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
	    float f11 = 0.1F + 0.3F * (orb.orbMaxAge - orb.orbAge) / orb.orbMaxAge;
	    GL11.glScalef(f11, f11, f11);
		BufferBuilder vb = tes.getBuffer();

		Color c = new Color(Color.HSBtoRGB(orb.world.getTotalWorldTime() /360F, 0.7F, 0.7F));
		GL11.glColor3f(c.getRed()/255F, c.getGreen()/255F, c.getBlue()/255F);
    	vb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
	    vb.normal(0.0F, 1.0F, 0.0F);
		vb.color(c.getRed(), c.getGreen(), c.getBlue(), 0);
	    vb.pos((0.0F - f7), (0.0F - f8), 0.0D).tex(f2, f5).endVertex();
	    vb.pos((f6 - f7), (0.0F - f8), 0.0D).tex(f3, f5).endVertex();
	    vb.pos((f6 - f7), (1.0F - f8), 0.0D).tex(f3, f4).endVertex();
	    vb.pos((0.0F - f7), (1.0F - f8), 0.0D).tex(f2, f4).endVertex();

	    tes.draw();
	    GlStateManager.disableBlend();
	    GL11.glDisable(32826);
		GlStateManager.resetColor();
	    GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityVisOrb entity) {
		return null;
	}

}
