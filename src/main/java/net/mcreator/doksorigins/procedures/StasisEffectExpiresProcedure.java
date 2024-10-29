package net.mcreator.doksorigins.procedures;

import net.minecraft.world.entity.Entity;

public class StasisEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.setNoGravity(false);
	}
}
