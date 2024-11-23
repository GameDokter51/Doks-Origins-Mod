package net.stasis.shatteredsoul.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.stasis.shatteredsoul.ShatteredSoul;

public class ShatteredSoulSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ShatteredSoul.MODID);
	public static final RegistryObject<SoundEvent> SUSPENSE_STRIKE = REGISTRY.register("suspense_strike", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("shatteredsoul", "suspense_strike")));
	public static final RegistryObject<SoundEvent> BELL_TOLL = REGISTRY.register("bell_toll", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("shatteredsoul", "bell_toll")));
	public static final RegistryObject<SoundEvent> EXPLOSION_HIT = REGISTRY.register("explode_hit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("shatteredsoul", "explode_hit")));
	public static final RegistryObject<SoundEvent> RICOCHET = REGISTRY.register("ricochet", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("shatteredsoul", "ricochet")));
}