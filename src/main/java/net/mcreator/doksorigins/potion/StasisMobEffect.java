
package net.mcreator.doksorigins.potion;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.doksorigins.procedures.StasisOnEffectActiveTickProcedure;
import net.mcreator.doksorigins.procedures.StasisEffectExpiresProcedure;

public class StasisMobEffect extends MobEffect {
	public StasisMobEffect() {
		super(MobEffectCategory.NEUTRAL, -13953739);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		StasisOnEffectActiveTickProcedure.execute(entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		StasisEffectExpiresProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
