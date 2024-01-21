package com.interplay.thaumicwandsremastered.item;

import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.core.data.SoulTicket;
import WayofTime.bloodmagic.iface.IBindable;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import com.interplay.thaumicwandsremastered.compat.PolyCompat;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import thaumcraft.api.items.IScribeTools;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ItemBloodwell extends ItemBase implements IScribeTools, IBindable {

    public ItemBloodwell(String name) {
        super(name);
        this.setMaxDamage(5);
        this.setMaxStackSize(1);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        if(PolyCompat.bloodMagic && stack.getItemDamage() > 0 && stack.hasTagCompound()) {
            if(!(entity instanceof EntityPlayer))
                return;

            EntityPlayer player = (EntityPlayer) entity;

            if(getBinding(stack) != null && NetworkHelper.getSoulNetwork(getBinding(stack)).syphon(SoulTicket.item(stack, 25)) != 0)
                stack.setItemDamage(stack.getItemDamage()-1);
            else if(player.getHealth() >= 6 && NetworkHelper.getSoulNetwork(player).syphonAndDamage(player, SoulTicket.item(stack, player.world, player, 100)).isSuccess())
                stack.setItemDamage(stack.getItemDamage()-1);

        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(!stack.hasTagCompound())
            return;

        if(PolyCompat.bloodMagic) {
            Binding binding = getBinding(stack);
            if(binding != null)
                tooltip.add(new TextComponentTranslation("tooltip.bloodmagic.currentOwner", binding.getOwnerName()).getFormattedText());
        }

    }

    @Override
    @ParametersAreNonnullByDefault
    public void setDamage(ItemStack stack, int damage) {
        if(PolyCompat.bloodMagic) {
            Binding binding = getBinding(stack);
            if(binding != null && damage > 0)
                if(NetworkHelper.getSoulNetwork(binding).syphon(SoulTicket.item(stack, 25)) != 0) {
                    super.setDamage(stack, 0);
                    return;
                }
        }

        super.setDamage(stack, damage);
    }

}
