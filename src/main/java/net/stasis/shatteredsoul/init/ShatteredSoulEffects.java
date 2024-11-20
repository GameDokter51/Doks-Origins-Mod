package net.stasis.shatteredsoul.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;
import net.stasis.shatteredsoul.effects.LockEffect;
import net.stasis.shatteredsoul.effects.StasisEffect;
import net.stasis.shatteredsoul.ShatteredSoul;

public class ShatteredSoulEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ShatteredSoul.MODID);
	public static final RegistryObject<MobEffect> STASIS = REGISTRY.register("stasis", () -> new StasisEffect());
	public static final RegistryObject<MobEffect> LOCK = REGISTRY.register("lock", () -> new LockEffect());
}
