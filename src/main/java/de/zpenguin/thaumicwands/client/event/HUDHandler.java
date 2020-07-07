package de.zpenguin.thaumicwands.client.event;

import java.awt.Color;
import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import de.zpenguin.thaumicwands.item.ItemWand;
import de.zpenguin.thaumicwands.item.TW_Items;
import de.zpenguin.thaumicwands.main.ThaumicWands;
import de.zpenguin.thaumicwands.util.WandHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.lib.events.HudHandler;
import thaumcraft.common.config.ModConfig;
import thaumcraft.common.world.aura.AuraChunk;

@EventBusSubscriber(modid = ThaumicWands.modID, value = Side.CLIENT)
public class HUDHandler {

	public static final ResourceLocation TC_HUD = new ResourceLocation("thaumcraft", "textures/gui/hud.png");
	private static final DecimalFormat smallFormatter = new DecimalFormat("####");
	private static final DecimalFormat largeFormatter = new DecimalFormat("#.#k");


	// Overrides shown Numbers

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void renderHUDHigh(RenderTickEvent e) {
		if(e.phase != TickEvent.Phase.START)
			if(Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity();
				if(player != null && Minecraft.getMinecraft().inGameHasFocus && Minecraft.isGuiEnabled())
					if(!WandHelper.getHeldWand(player).isEmpty()) {
					    double amount = RechargeHelper.getCharge(WandHelper.getHeldWand(player));
					    double max = ((ItemWand) TW_Items.itemWand).getMaxCharge(WandHelper.getHeldWand(player), player);
					    HudHandler.currentAura = new AuraChunk(null, (short) max, (float) amount, 0);
					}
			}
	}

	// Actually Renders the Bar

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void renderHUDLow(RenderTickEvent e) {
		if(e.phase != TickEvent.Phase.START)
			if(Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity();
				if(player != null && Minecraft.getMinecraft().inGameHasFocus && Minecraft.isGuiEnabled())
					if(!WandHelper.getHeldWand(player).isEmpty())
						renderWandHUD(player, e.renderTickTime);
			}
	}

	private static void renderWandHUD(EntityPlayer player, float renderTickTime) {
		short short1 = 240;
	    short short2 = 240;


	    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, short1 / 1.0F, short2 / 1.0F);
	    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

	    GL11.glPushMatrix();

	    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
	    GL11.glClear(256);
	    GL11.glMatrixMode(5889);
	    GL11.glLoadIdentity();
	    GL11.glOrtho(0.0D, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
	    GL11.glMatrixMode(5888);
	    GL11.glLoadIdentity();
	    int l = sr.getScaledHeight();

	    int dialLocation = ModConfig.CONFIG_GRAPHICS.dialBottom ? l - 32 : 0;

	    GL11.glTranslatef(0.0F, dialLocation, -2000.0F);

	    GL11.glEnable(3042);
	    GL11.glBlendFunc(770, 771);

	    Minecraft.getMinecraft().renderEngine.bindTexture(TC_HUD);

	    GL11.glTranslatef(16.0F, 16.0F, 0.0F);

	    double amount = RechargeHelper.getCharge(WandHelper.getHeldWand(player));
	    double max = ((ItemWand) TW_Items.itemWand).getMaxCharge(WandHelper.getHeldWand(player), player);

	    HudHandler.currentAura = new AuraChunk(null, (short) max, (float) amount, 0);
	    GL11.glPushMatrix();

	    GL11.glTranslatef(16.0F, -10.0F, 0.0F);
	    GL11.glScaled(0.5D, 0.5D, 0.5D);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

	    int loc = (int) (30 * amount / max);

	    GL11.glPushMatrix();
	    Color ac = new Color(Aspect.MAGIC.getColor());

	    GlStateManager.disableBlend();
	    GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
	    UtilsFX.drawTexturedQuad(-4.0F, 5F, 104.0F, 0.0F, 0F, 0F, -90.0D);
	    GlStateManager.enableBlend();

	    GL11.glColor4f(ac.getRed() / 255.0F, ac.getGreen() / 255.0F, ac.getBlue() / 255.0F, 0.8F);
	    UtilsFX.drawTexturedQuad(-4.0F, 35 - loc, 104.0F, 0.0F, 8.0F, loc, -90.0D);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    GL11.glPopMatrix();

	    GL11.glPushMatrix();
	    UtilsFX.drawTexturedQuad(-8.0F, -3.0F, 72.0F, 0.0F, 16.0F, 42.0F, -90.0D);
	    GL11.glPopMatrix();

	    if (player.isSneaking()) {
	    	GL11.glPushMatrix();
	    	GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
	    	String msg;
	    	if (MathHelper.floor(amount) > 9999) msg = largeFormatter.format(amount / 1000);
	    	else msg = smallFormatter.format(amount);
	    	Minecraft.getMinecraft().ingameGUI.drawString(Minecraft.getMinecraft().fontRenderer, msg, -32, -4, 16777215);
	    	GL11.glPopMatrix();

	    }

	    GL11.glPopMatrix();

	    GL11.glDisable(3042);

	    GL11.glPopMatrix();
	}


}
