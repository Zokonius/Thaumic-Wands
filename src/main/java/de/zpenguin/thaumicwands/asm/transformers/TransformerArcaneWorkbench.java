package de.zpenguin.thaumicwands.asm.transformers;

import java.util.Iterator;

import org.apache.logging.log4j.Level;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import de.zpenguin.thaumicwands.asm.WandTransformer;
import de.zpenguin.thaumicwands.main.TW_GuiHandler;
import de.zpenguin.thaumicwands.main.ThaumicWands;
import de.zpenguin.thaumicwands.tile.TileArcaneWorkbenchNew;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TransformerArcaneWorkbench implements Opcodes {

	private static boolean running = false;

	public static byte[] transform(byte[] origCode) {
		if(!running) {
			origCode = transformGui(origCode);
			origCode = transformTile(origCode);
			running = true;
		}
		return origCode;

	}

	public static byte[] transformGui(byte[] origCode) {

		ThaumicWands.logger.log(Level.INFO, "[CORE] Patching Arcane Workbench");

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
				endList.add(new MethodInsnNode(INVOKESTATIC, "de/zpenguin/thaumicwands/asm/transformers/TransformerArcaneWorkbench", "b_onBlockActivated", desc, false));
				methodNode.instructions.insertBefore(insn, endList);

			}

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(cw);

		return cw.toByteArray();
	}

	public static byte[] transformTile(byte[] origCode) {
		final String methodName = WandTransformer.isDeobfEnvironment ? "createNewTileEntity": "func_149915_a";
		final String desc = "(Lnet/minecraft/world/World;I)Lnet/minecraft/tileentity/TileEntity;";

		ClassReader cr = new ClassReader(origCode);
		ClassNode classNode = new ClassNode();
		cr.accept(classNode, 0);

		MethodNode method = new MethodNode(ACC_PUBLIC, methodName, desc, null, null);
		InsnList instrcutions = new InsnList();
			instrcutions.add(new VarInsnNode(ALOAD, 1));
			instrcutions.add(new VarInsnNode(ILOAD, 2));
			instrcutions.add(new MethodInsnNode(INVOKESTATIC, "de/zpenguin/thaumicwands/asm/transformers/TransformerArcaneWorkbench", "t_createNewTileEntity", desc, false));
			instrcutions.add(new InsnNode(ARETURN));
			method.instructions.add(instrcutions);
		classNode.methods.add(method);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(cw);

		return cw.toByteArray();
	}

	public static boolean b_onBlockActivated(World world, BlockPos pos, EntityPlayer player) {
		if(world.isRemote)
			return true;
		player.openGui(ThaumicWands.instance, TW_GuiHandler.guiArcaneWorkbench, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	public static TileEntity t_createNewTileEntity(World world, int meta) {
		return new TileArcaneWorkbenchNew();
	}

}
