
package net.noxaeterna.shatteredsoul.effects;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class StasisEffect extends MobEffect {

	public StasisEffect() {
		super(MobEffectCategory.NEUTRAL, -13953739);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity == null)
			return;
		entity.setDeltaMovement(new Vec3(0.0, 0.0, 0.0));
		entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.25, 0.05, 0.25));
		entity.fallDistance = 0;
		entity.setNoGravity(true);
		if (entity instanceof Mob _entity)
			_entity.getNavigation().stop();
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		if (entity == null)
			return;
		entity.setNoGravity(false);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
