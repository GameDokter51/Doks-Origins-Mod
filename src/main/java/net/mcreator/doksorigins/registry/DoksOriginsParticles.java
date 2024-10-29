package net.mcreator.doksorigins.registry;

import mod.maxbogomol.fluffy_fur.client.particle.type.GenericParticleType;
import net.mcreator.doksorigins.DoksOriginsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DoksOriginsParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, DoksOriginsMod.MODID);

    public static RegistryObject<GenericParticleType> CHAINS = PARTICLES.register("chains", GenericParticleType::new);
    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }

    @Mod.EventBusSubscriber(modid = DoksOriginsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientRegistryEvents {
        @SubscribeEvent
        public static void registerParticles(RegisterParticleProvidersEvent event) {
            ParticleEngine particleEngine = Minecraft.getInstance().particleEngine;
            particleEngine.register(CHAINS.get(), GenericParticleType.Factory::new);
        }
    }
}