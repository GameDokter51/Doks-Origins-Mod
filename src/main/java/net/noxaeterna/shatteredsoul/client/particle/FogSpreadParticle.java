package net.noxaeterna.shatteredsoul.client.particle;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;

import mod.maxbogomol.fluffy_fur.client.particle.ParticleBuilder;
import mod.maxbogomol.fluffy_fur.client.particle.behavior.ParticleBehavior;
import mod.maxbogomol.fluffy_fur.client.particle.data.ColorParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.GenericParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.LightParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.SpinParticleData;
import mod.maxbogomol.fluffy_fur.common.easing.Easing;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurParticles;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import net.minecraft.client.multiplayer.ClientLevel;

@OnlyIn(Dist.CLIENT)
public class FogSpreadParticle extends TextureSheetParticle {

    FogSpreadParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites ,double velX, double velY, double velZ) {
        super(world, x, y + 0.1, z, 0.0, 0.0, 0.0);
        this.quadSize = (float) 0;
        this.setParticleSpeed(0D, 0D, 0D);
        this.lifetime = (int) 0;
        this.setSpriteFromAge(sprites);
		
		if (level.isClientSide()) {
			for (int i = 0; i < 30; i++) {
				double angle = Math.toRadians(i * (360.0 / 30));  // Convert degree to radians, dividing the circle into 20 segments
				double distance = 0.3;  // Set a fixed distance from the center (radius of the circle)

				double xOffset = Math.cos(angle) * distance;
				double zOffset = Math.sin(angle) * distance;

				ParticleBuilder.create(FluffyFurParticles.WISP)
					.setRenderType(FluffyFurRenderTypes.TRANSLUCENT_PARTICLE)
					.setBehavior(ParticleBehavior.create(90, 0, 0)
							.setXSpinData(SpinParticleData.create().randomOffsetDegrees(-5, 5).build())
							.setYSpinData(SpinParticleData.create().randomOffsetDegrees(-5, 5).build())
							.setZSpinData(SpinParticleData.create().randomOffset().randomSpin(0.5f).build())
							.build())
					.setColorData(ColorParticleData.create(0.7f, 1f, 1f, 0f, 0.01f, 0.01f).build())
					.setTransparencyData(GenericParticleData.create(0.05f, 0).setEasing(Easing.QUARTIC_OUT).build())
					.setScaleData(GenericParticleData.create(0.1f, 3, 0).setEasing(Easing.ELASTIC_OUT).build())
					.setLightData(LightParticleData.DEFAULT)
					.setLifetime(70, 120)
					.setVelocity(xOffset, 0, zOffset)
					.repeat(level, x, y + 0.35, z, 1);

				ParticleBuilder.create(FluffyFurParticles.WISP)
					.setRenderType(FluffyFurRenderTypes.TRANSLUCENT_PARTICLE)
					.setBehavior(ParticleBehavior.create(90, 0, 0)
							.setXSpinData(SpinParticleData.create().randomOffsetDegrees(-5, 5).build())
							.setYSpinData(SpinParticleData.create().randomOffsetDegrees(-5, 5).build())
							.setZSpinData(SpinParticleData.create().randomOffset().randomSpin(0.5f).build())
							.build())
					.setColorData(ColorParticleData.create(0.15f, 0.3f, 0.3f, 0f, 0.01f, 0.01f).build())
					.setTransparencyData(GenericParticleData.create(0.045f, 0).setEasing(Easing.QUARTIC_OUT).build())
					.setScaleData(GenericParticleData.create(0.1f, 3, 0).setEasing(Easing.ELASTIC_OUT).build())
					.setLightData(LightParticleData.DEFAULT)
					.setLifetime(100, 100)
					.setVelocity(xOffset, 0, zOffset)
					.repeat(level, x, y + 0.1, z, 1);
			}
		}
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        return super.getLightColor(pPartialTick);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
                                        
            return new FogSpreadParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}