
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.doksorigins.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.doksorigins.potion.StasisMobEffect;
import net.mcreator.doksorigins.DoksOriginsMod;

public class DoksOriginsModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DoksOriginsMod.MODID);
	public static final RegistryObject<MobEffect> STASIS = REGISTRY.register("stasis", () -> new StasisMobEffect());
}
