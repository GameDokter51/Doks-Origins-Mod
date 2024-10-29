
package net.mcreator.doksorigins.item;

import mod.maxbogomol.fluffy_fur.client.particle.ParticleBuilder;
import mod.maxbogomol.fluffy_fur.client.particle.behavior.ParticleBehavior;
import mod.maxbogomol.fluffy_fur.client.particle.data.ColorParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.GenericParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.LightParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.SpinParticleData;
import mod.maxbogomol.fluffy_fur.common.easing.Easing;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurParticles;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.doksorigins.procedures.ContractPlayerSignsProcedure;
import net.minecraft.world.phys.Vec3;

public class ContractItem extends Item {
	public ContractItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.BLOCK;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 20;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		CompoundTag nbt = stack.getOrCreateTag();

		ContractPlayerSignsProcedure.execute(level, player.getX(), player.getY(), player.getZ(), player, stack);

		if (level.isClientSide()) {
			Vec3 pos = player.position();
			for (int i = 0; i < 120; i++) {
				double angle = Math.toRadians(i * (360.0 / 120));  // Convert degree to radians, dividing the circle into 20 segments
				double distance = 0.3;  // Set a fixed distance from the center (radius of the circle)

				double xOffset = Math.cos(angle) * distance;
				double zOffset = Math.sin(angle) * distance;

				ParticleBuilder.create(FluffyFurParticles.WISP)
						.setRenderType(FluffyFurRenderTypes.TRANSLUCENT_PARTICLE)
						.setBehavior(ParticleBehavior.create(90, 0, 0)
								.setXSpinData(SpinParticleData.create().randomOffsetDegrees(-5, 5).build())
								.setYSpinData(SpinParticleData.create().randomOffsetDegrees(-5, 5).build())
								.setZSpinData(SpinParticleData.create().randomOffset().randomSpin(0.5f).build())
								.build())
						.setColorData(ColorParticleData.create(0.7f, 1f, 1f, 0f, 0.01f, 0.01f).build())
						.setTransparencyData(GenericParticleData.create(0.05f, 0).setEasing(Easing.QUARTIC_OUT).build())
						.setScaleData(GenericParticleData.create(0.1f, 3, 0).setEasing(Easing.ELASTIC_OUT).build())
						.setLightData(LightParticleData.DEFAULT)
						.setLifetime(70, 120)
						.setVelocity(xOffset, 0, zOffset)
						.repeat(level, pos.x(), pos.y() + 0.35, pos.z(), 1);

				ParticleBuilder.create(FluffyFurParticles.WISP)
						.setRenderType(FluffyFurRenderTypes.TRANSLUCENT_PARTICLE)
						.setBehavior(ParticleBehavior.create(90, 0, 0)
								.setXSpinData(SpinParticleData.create().randomOffsetDegrees(-5, 5).build())
								.setYSpinData(SpinParticleData.create().randomOffsetDegrees(-5, 5).build())
								.setZSpinData(SpinParticleData.create().randomOffset().randomSpin(0.5f).build())
								.build())
						.setColorData(ColorParticleData.create(0.15f, 0.3f, 0.3f, 0f, 0.01f, 0.01f).build())
						.setTransparencyData(GenericParticleData.create(0.045f, 0).setEasing(Easing.QUARTIC_OUT).build())
						.setScaleData(GenericParticleData.create(0.1f, 3, 0).setEasing(Easing.ELASTIC_OUT).build())
						.setLightData(LightParticleData.DEFAULT)
						.setLifetime(100, 100)
						.setVelocity(xOffset, 0, zOffset)
						.repeat(level, pos.x(), pos.y() + 0.1, pos.z(), 1);
			}
		}
		return InteractionResultHolder.success(stack);
	}
}