package net.mcreator.doksorigins.network;

import mod.maxbogomol.fluffy_fur.FluffyFur;
import mod.maxbogomol.fluffy_fur.client.particle.ParticleBuilder;
import mod.maxbogomol.fluffy_fur.client.particle.behavior.SparkParticleBehavior;
import mod.maxbogomol.fluffy_fur.client.particle.data.ColorParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.GenericParticleData;
import mod.maxbogomol.fluffy_fur.client.screenshake.ScreenshakeHandler;
import mod.maxbogomol.fluffy_fur.client.screenshake.ScreenshakeInstance;
import mod.maxbogomol.fluffy_fur.common.easing.Easing;
import mod.maxbogomol.fluffy_fur.common.network.PositionClientPacket;
import mod.maxbogomol.fluffy_fur.config.FluffyFurClientConfig;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurParticles;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import net.mcreator.doksorigins.DoksOriginsMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import mod.maxbogomol.fluffy_fur.client.particle.behavior.TrailParticleBehavior;
import java.util.function.Supplier;

import static com.mojang.text2speech.Narrator.LOGGER;

public class TestPacket extends PositionClientPacket {

    public TestPacket(double x, double y, double z) {
        super(x, y, z);
        LOGGER.info("Coords are " + x + ", " + y + ", " + z + ".");
    }
    public TestPacket(Vec3 vec) {
        super(vec);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void execute(Supplier<NetworkEvent.Context> context) {
            Level level = FluffyFur.proxy.getLevel();
            Vec3 pos = new Vec3(x, y, z);
            if (level != null) {
                LOGGER.info("There should be an explosion here.");

                ParticleBuilder.create(FluffyFurParticles.WISP)
                        .setColorData(ColorParticleData.create(1, 0.3f, 0, 0, 0, 0).build())
                        .setTransparencyData(GenericParticleData.create(0.5f, 0.1f).setEasing(Easing.QUARTIC_OUT).build())
                        .setScaleData(GenericParticleData.create(8f, 50f, 120).setEasing(Easing.CUBIC_IN).build())
                        .setLifetime(300,200)
                        .randomVelocity(0.7f, 0.65f, 0.7f)
                        .repeat(level, pos.x(), pos.y(), pos.z(), 60);

                ParticleBuilder.create(FluffyFurParticles.TRAIL)
                        .setRenderType(FluffyFurRenderTypes.ADDITIVE_PARTICLE_TEXTURE)
                        .setBehavior(TrailParticleBehavior.create()
                                .setTransparencyData(GenericParticleData.create(1, 1, 0).setEasing(Easing.QUARTIC_OUT).build())
                                .build())
                        .setColorData(ColorParticleData.create(1, 0.3f, 0, 0.9f, 0.15f, 0).build())
                        .setTransparencyData(GenericParticleData.create(1, 1, 0).setEasing(Easing.QUARTIC_OUT).build())
                        .setScaleData(GenericParticleData.create(2,3f,0).setEasing(Easing.ELASTIC_OUT).build())
                        .setLifetime(200, 100)
                        .randomVelocity(1.6f,2f,1.6f)
                        .setGravity(0.4f)
                        .repeat(level, pos.x(), pos.y(), pos.z(), 75);

                ParticleBuilder.create(FluffyFurParticles.SMOKE)
                        .setRenderType(FluffyFurRenderTypes.TRANSLUCENT_PARTICLE)
                        .setColorData(ColorParticleData.create(0.3f, 0.1f, 0f, 0f, 0f, 0f).build())
                        .setTransparencyData(GenericParticleData.create(0.2f,0.35f,0f).setEasing(Easing.QUARTIC_OUT).build())
                        .randomVelocity(1.7f, 1.6f, 1.7f)
                        .setScaleData(GenericParticleData.create(7f, 20f, 35).setEasing(Easing.QUINTIC_OUT).build())
                        .setLifetime(700)
                        .setGravity(0.04f)
                        .repeat(level, pos.x(), pos.y(), pos.z(), 100);

                ParticleBuilder.create(FluffyFurParticles.SMOKE)
                        .setColorData(ColorParticleData.create(1f, 0.3f, 0f, 0f, 0f, 0f).build())
                        .setTransparencyData(GenericParticleData.create(0.5f,0.05f,0f).setEasing(Easing.QUARTIC_OUT).build())
                        .randomVelocity(0.5f, 0.65f, 0.5f)
                        .setScaleData(GenericParticleData.create(7f, 10f, 20f).setEasing(Easing.QUINTIC_OUT).build())
                        .setLifetime(700)
                        .repeat(level, pos.x(), pos.y(), pos.z(), 20);
                ScreenshakeHandler.addScreenshake(new ScreenshakeInstance(130).setIntensity(0.35f, 0).setEasing(Easing.QUINTIC_IN_OUT));
                level.playSound(null, pos.x(), pos.y(), pos.z(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.PLAYERS, 200f, 0.1f);
            }
    }


    public static void register(SimpleChannel instance, int index) {
        instance.registerMessage(index, TestPacket.class, TestPacket::encode, TestPacket::decode, TestPacket::handle);
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
    }

    public static TestPacket decode(FriendlyByteBuf buf) {
        return new TestPacket(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }
}