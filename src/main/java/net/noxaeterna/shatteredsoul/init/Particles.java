package net.noxaeterna.shatteredsoul.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

import net.minecraftforge.api.distmarker.Dist;
import net.noxaeterna.shatteredsoul.client.particle.FogSpreadParticle;
import net.noxaeterna.shatteredsoul.client.particle.ImpactParticle;
import net.noxaeterna.shatteredsoul.client.particle.RayParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Particles {

	@SubscribeEvent
	public static void registerParticles(final RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ParticleTypes.FOG_SPREAD.get(), FogSpreadParticle.Provider::new);
		event.registerSpriteSet(ParticleTypes.IMPACT.get(), ImpactParticle.Provider::new);
		event.registerSpriteSet(ParticleTypes.RAY.get(), RayParticle.Provider::new);
	}
}