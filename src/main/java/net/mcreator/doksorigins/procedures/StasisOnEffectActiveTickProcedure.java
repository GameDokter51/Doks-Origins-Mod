package net.mcreator.doksorigins.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;

public class StasisOnEffectActiveTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.setDeltaMovement(new Vec3(0.000001, 0.000001, 0.000001));
		entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.25, 0.05, 0.25));
		entity.fallDistance = 0;
		entity.setNoGravity(true);
		if (entity instanceof Mob _entity)
			_entity.getNavigation().stop();
	}
}
