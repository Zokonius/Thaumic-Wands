package com.interplay.thaumicwandsremastered.main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import thaumcraft.client.gui.GuiArcaneWorkbench;
import thaumcraft.common.container.ContainerArcaneWorkbench;
import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;

public class TWR_GuiHandler implements IGuiHandler {

    public static final int guiArcaneWorkbench = 0;

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        switch (ID){
        case guiArcaneWorkbench:
            return new GuiArcaneWorkbench(player.inventory, (TileArcaneWorkbench) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        switch (ID){
        case guiArcaneWorkbench:
            return new ContainerArcaneWorkbench(player.inventory, (TileArcaneWorkbench) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

}
