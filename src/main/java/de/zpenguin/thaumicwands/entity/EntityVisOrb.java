package de.zpenguin.thaumicwands.entity;

import java.util.List;

import de.zpenguin.thaumicwands.util.WandHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.items.RechargeHelper;

public class EntityVisOrb extends Entity {

	public int orbAge = 0;
	public int orbMaxAge = 150;
	public int orbCooldown;
	private int orbHealth = 5;
	private int visAmount;
	private EntityPlayer closestPlayer;

	public EntityVisOrb(World world, double x, double y, double z, int amount) {
		super(world);
		setSize(0.125F, 0.125F);
		setPosition(x, y, z);
		this.rotationYaw = (float) (Math.random() * 360.0D);
		this.motionX = ((float) (Math.random() * 0.2D - 0.1D) * 2.0F);
		this.motionY = ((float) (Math.random() * 0.2D) * 2.0F);
		this.motionZ = ((float) (Math.random() * 0.2D - 0.1D) * 2.0F);
		this.visAmount = amount;
	}

	public EntityVisOrb(World world) {
		super(world);
		setSize(0.125F, 0.125F);
	}

	@Override
	public boolean isInRangeToRenderDist(double distance) {
		double d1 = 32 * getRenderDistanceWeight();
		return distance < d1 * d1;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(orbCooldown > 0)
			orbCooldown--;
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.03D;
		if(this.world.getBlockState(new BlockPos(posX, posY, posZ)).getMaterial() == Material.LAVA) {
			this.motionY = 0.2;
			this.motionX = ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
			this.motionZ = ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
			playSound(new SoundEvent(new ResourceLocation("random.fizz")), 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
		}

		double d0 = 8.0D;
		if(this.ticksExisted % 5 == 0 && this.closestPlayer == null) {
			List<Entity> targets = this.world.getEntitiesWithinAABB(EntityPlayer.class,
					new AxisAlignedBB(posX, posY, posZ, posX, posY, posZ).expand(d0, d0, d0));
			if(targets.size() > 0) {
				double distance = Double.MAX_VALUE;
				for(Entity t : targets) {
					double d = ((EntityPlayer) t).getDistanceSq(this);
					if(d < distance && !WandHelper.isWandInHotbarWithRoom((EntityPlayer) t, visAmount).isEmpty()) {
						distance = d;
						this.closestPlayer = (EntityPlayer) t;
					}
				}
			}
		}
		if(this.closestPlayer != null) {
			double d1 = (this.closestPlayer.posX - this.posX) / d0;
			double d2 = (this.closestPlayer.posY + this.closestPlayer.getEyeHeight() - this.posY) / d0;
			double d3 = (this.closestPlayer.posZ - this.posZ) / d0;
			double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
			double d5 = 1.0D - d4;
			if(d5 > 0.0D) {
				d5 *= d5;
				this.motionX += d1 / d4 * d5 * 0.1D;
				this.motionY += d2 / d4 * d5 * 0.1D;
				this.motionZ += d3 / d4 * d5 * 0.1D;
			}
		}

		move(MoverType.PLAYER, this.motionX, this.motionY, this.motionZ);
		float f = 0.98F;
		if(this.onGround) {
			f = 0.6F;
			IBlockState state = this.world.getBlockState(new BlockPos(posX, posY, posZ));
			if(!state.getBlock().isAir(state, (IBlockAccess) this.world,
					new BlockPos(posX, this.getCollisionBoundingBox().minY - 1, this.posZ)))
				f = state.getBlock().slipperiness * 0.98F;
		}
		this.motionX *= f;
		this.motionY *= f;
		this.motionZ *= f;
		if(this.onGround)
			this.motionY *= -0.9;
		this.orbAge++;
		if(this.orbAge >= this.orbMaxAge)
			setDead();

	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		if(!world.isRemote) {
			ItemStack wand = WandHelper.isWandInHotbarWithRoom(player, this.visAmount);
			if(this.orbCooldown == 0 && player.xpCooldown == 0 && !wand.isEmpty()) {
				RechargeHelper.rechargeItemBlindly(wand, player, this.visAmount);
				player.xpCooldown = 2;
				playSound(new SoundEvent(new ResourceLocation("random.orb")), 0.4F,
						2.0F + this.rand.nextFloat() * 0.4F);
				setDead();
			}
		}
	}

	@Override
	public boolean handleWaterMovement() {
		return world.handleMaterialAcceleration(this.getCollisionBoundingBox(), Material.WATER, this);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		return new AxisAlignedBB(0, 0, 0, 0.1, 0, 0.1);
	}

	@Override
	protected void dealFireDamage(int amount) {
		attackEntityFrom(DamageSource.ON_FIRE, amount);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.orbHealth = compound.getShort("Health");
		this.orbAge = compound.getShort("Age");
		this.visAmount = compound.getShort("Amount");

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setShort("Health", (short) orbHealth);
		compound.setShort("Age", (short) orbAge);
		compound.setShort("Amount", (short) visAmount);
	}

	@Override
	public int getBrightnessForRender() {
		return super.getBrightnessForRender();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(isEntityInvulnerable(source))
			return false;
		markVelocityChanged();
		if((orbHealth -= amount) <= 0)
			setDead();
		return false;
	}

	@Override
	public boolean canBeAttackedWithItem() {
		return false;
	}

}
