package net.noxaeterna.shatteredsoul.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import mod.maxbogomol.fluffy_fur.client.particle.GenericParticle;
import mod.maxbogomol.fluffy_fur.client.particle.ICustomParticleRender;
import mod.maxbogomol.fluffy_fur.client.particle.behavior.ICustomBehaviorParticleRender;
import mod.maxbogomol.fluffy_fur.client.particle.type.GenericParticleType;
import mod.maxbogomol.fluffy_fur.client.render.LevelRenderHandler;
import net.minecraft.core.particles.ParticleType;
import net.noxaeterna.shatteredsoul.ShatteredSoul;
import net.noxaeterna.shatteredsoul.client.particle.*;

public class ShatteredSoulParticles {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ShatteredSoul.MODID);

	public static final RegistryObject<SimpleParticleType> RAY = REGISTRY.register("ray", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SHOCKWAVE = REGISTRY.register("shockwave", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> FOG_SPREAD = REGISTRY.register("fog_spread", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> CRYSTAL = REGISTRY.register("crystal", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> BOOMSPHERE = REGISTRY.register("boomsphere", () -> new SimpleParticleType(true));

	public static RegistryObject<GenericParticleType> CRYSTAL_BASE = REGISTRY.register("crystal_base", GenericParticleType::new);
	
    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientRegistry {

		@SubscribeEvent
		public static void registerParticles(final RegisterParticleProvidersEvent event) {
			event.registerSpriteSet(RAY.get(), RayParticle.Provider::new);
			event.registerSpriteSet(SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
			event.registerSpriteSet(FOG_SPREAD.get(), FogSpreadParticle.FogSpreadParticleProvider::new);
			event.registerSpriteSet(CRYSTAL.get(), CrystalParticle.CrystalParticleProvider::new);
			event.registerSpriteSet(BOOMSPHERE.get(), BoomsphereParticle.BoomsphereParticleProvider::new);

			event.registerSpriteSet(CRYSTAL_BASE.get(), GenericParticleType.Factory::new);
		}

		public static void addParticleList(ICustomParticleRender particle) {
			LevelRenderHandler.particleList.add(particle);
		}

		public static void addBehaviorParticleList(GenericParticle particle, ICustomBehaviorParticleRender behavior) {
			LevelRenderHandler.behaviorParticleList.put(particle, behavior);
		}
	}
}