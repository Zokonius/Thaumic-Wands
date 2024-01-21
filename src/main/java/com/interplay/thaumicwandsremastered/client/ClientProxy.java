package com.interplay.thaumicwandsremastered.client;

import com.interplay.thaumicwandsremastered.entiy.EntityVisOrb;
import com.interplay.thaumicwandsremastered.item.TWR_Items;
import com.interplay.thaumicwandsremastered.main.CommonProxy;
import com.interplay.thaumicwandsremastered.main.ThaumicWandsRemastered;
import com.interplay.thaumicwandsremastered.tile.TileArcaneWorkbenchNew;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ThaumicWandsRemastered.MODID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        TWR_Items.itemWand.setTileEntityItemStackRenderer(new ItemWandRenderer());
    }

    @Override
    @Deprecated
    public void init(FMLInitializationEvent e) {
        super.init(e);
        ClientRegistry.bindTileEntitySpecialRenderer(TileArcaneWorkbenchNew.class, new TileArcaneWorktableRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityVisOrb.class, new EntityVisOrbRenderer(Minecraft.getMinecraft().getRenderManager()));
    }

    @SubscribeEvent
    public static void bakeModel(ModelBakeEvent e) {
        e.getModelRegistry().putObject(new ModelResourceLocation("thaumicwandsremastered:item_wand_3d"), new BakedModelWand());
    }

}
