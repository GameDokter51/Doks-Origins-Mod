package net.noxaeterna.shatteredsoul.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.noxaeterna.shatteredsoul.ShatteredSoul;

public class ParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ShatteredSoul.MODID);
	public static final RegistryObject<SimpleParticleType> FOG_SPREAD = REGISTRY.register("fog_spread", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> IMPACT = REGISTRY.register("impact", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> RAY = REGISTRY.register("ray", () -> new SimpleParticleType(false));
}