package de.zpenguin.thaumicwands.asm;

import de.zpenguin.thaumicwands.asm.transformers.TransformerArcaneWorkbench;
import de.zpenguin.thaumicwands.asm.transformers.TransformerArcaneWorkbenchCharger;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;

public class WandTransformer implements IClassTransformer {

	public static boolean isDeobfEnvironment;


	@Override
	public byte[] transform(String className, String transformedName, byte[] origCode) {
		isDeobfEnvironment = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");

		if(className.equals("thaumcraft.common.blocks.crafting.BlockArcaneWorkbench"))
			return TransformerArcaneWorkbench.transform(origCode);
		if(className.equals("thaumcraft.common.blocks.crafting.BlockArcaneWorkbenchCharger"))
			return TransformerArcaneWorkbenchCharger.transform(origCode);
		return origCode;
	}

}
