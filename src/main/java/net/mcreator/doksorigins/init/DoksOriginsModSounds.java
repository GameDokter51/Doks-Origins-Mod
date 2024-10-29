
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.doksorigins.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.doksorigins.DoksOriginsMod;

public class DoksOriginsModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DoksOriginsMod.MODID);
	public static final RegistryObject<SoundEvent> SUSPENSE_STRIKE = REGISTRY.register("suspense_strike", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("doks_origins", "suspense_strike")));
	public static final RegistryObject<SoundEvent> BELL_TOLL = REGISTRY.register("bell_toll", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("doks_origins", "bell_toll")));
	public static final RegistryObject<SoundEvent> LIMINALMONO = REGISTRY.register("liminalmono", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("doks_origins", "liminalmono")));
	public static final RegistryObject<SoundEvent> LIMINALSTEREO = REGISTRY.register("liminalstereo", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("doks_origins", "liminalstereo")));
	public static final RegistryObject<SoundEvent> RICOCHET = REGISTRY.register("ricochet", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("doks_origins", "ricochet")));
}
