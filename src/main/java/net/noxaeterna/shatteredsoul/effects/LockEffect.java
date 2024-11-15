
package net.noxaeterna.shatteredsoul.effects;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class LockEffect extends MobEffect {

	public LockEffect() {
		super(MobEffectCategory.HARMFUL, -13953739);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity == null)
			return;
		entity.setDeltaMovement(new Vec3(0.0f, 0.0f, 0.0f));
		
		entity.setYRot(entity.getYRot());
		entity.setXRot(entity.getXRot());
		entity.setPos(entity.getPosition(0.0f));


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
