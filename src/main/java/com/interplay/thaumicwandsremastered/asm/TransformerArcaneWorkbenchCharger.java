package com.interplay.thaumicwandsremastered.asm;

import com.interplay.thaumicwandsremastered.main.TWR_GuiHandler;
import com.interplay.thaumicwandsremastered.main.ThaumicWandsRemastered;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import thaumcraft.Thaumcraft;
import thaumcraft.api.blocks.BlocksTC;

import java.util.Iterator;

public class TransformerArcaneWorkbenchCharger implements Opcodes {

    private static boolean running = false;

    public static byte[] transform(byte[] origCode) {
        if(!running) {
            origCode = transformGui(origCode);
            running = true;
        }
        return origCode;

    }

    public static byte[] transformGui(byte[] origCode) {

        ThaumicWandsRemastered.logger.log(Level.INFO, "[CORE] Patching Workbench Charger");

        final String methodToPatch = WandTransformer.isDeobfEnvironment ? "onBlockActivated": "func_180639_a";
        final String desc = "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/EntityPlayer;)Z";

        ClassReader cr = new ClassReader(origCode);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, 0);
        for(MethodNode methodNode : classNode.methods)
            if(methodNode.name.equals(methodToPatch)) {

                Iterator<AbstractInsnNode> insnNodes = methodNode.instructions.iterator();
                AbstractInsnNode insn = null;
                while(insnNodes.hasNext()) {
                    insn = insnNodes.next();
                    if(insn.getOpcode() == IRETURN)
                        break;
                    methodNode.instructions.remove(insn);

                }
                InsnList endList = new InsnList();
                endList.add(new VarInsnNode(ALOAD, 1));
                endList.add(new VarInsnNode(ALOAD, 2));
                endList.add(new VarInsnNode(ALOAD, 4));
                endList.add(new MethodInsnNode(INVOKESTATIC,
                        "com/interplay/thaumicwandsremastered/asm/TransformerArcaneWorkbenchCharger", "b_onBlockActivated",
                        desc, false));
                methodNode.instructions.insertBefore(insn, endList);

            }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classNode.accept(cw);

        return cw.toByteArray();
    }


    public static boolean b_onBlockActivated(World world, BlockPos pos, EntityPlayer player) {
        if (world.isRemote)
            return true;
        if (world.getBlockState(pos.down()).getBlock() == BlocksTC.arcaneWorkbench)
            player.openGui(ThaumicWandsRemastered.instance, TWR_GuiHandler.guiArcaneWorkbench, world, pos.getX(), pos.getY()-1, pos.getZ());
        if (world.getBlockState(pos.down()).getBlock() == BlocksTC.wandWorkbench)
            player.openGui(Thaumcraft.instance, 7, world, pos.getX(), pos.down().getY(), pos.getZ());
        return true;
    }

}