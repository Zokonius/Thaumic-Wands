package de.zpenguin.thaumicwands.util.research;

import net.minecraft.block.Block;
import thaumcraft.api.research.theorycraft.ITheorycraftAid;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class AidBase implements ITheorycraftAid {
    
    Block block;
    Class<TheorycraftCard>[] cards;
    
    public AidBase(Block block, Class<? extends TheorycraftCard>[] cards) {
        this.block = block;
        this.cards = (Class<TheorycraftCard>[]) cards;
    }
    
    @Override
    public Object getAidObject() {
        return block;
    }
    
    @Override
    public Class<TheorycraftCard>[] getCards() {
        return cards;
    }
    
}
