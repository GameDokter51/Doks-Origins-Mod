package net.stasis.shatteredsoul.client.particle;

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
	public static FogSpreadParticleProvider provider(SpriteSet spriteSet) {
		return new FogSpreadParticleProvider(spriteSet);
	}

	public static class FogSpreadParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public FogSpreadParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			FogSpreadParticle particle = new FogSpreadParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
			
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
			return particle;
		}
	}

	@SuppressWarnings("unused")
	private final SpriteSet spriteSet;

	protected FogSpreadParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(0f, 0f);
		this.quadSize = 0.0f;
		this.lifetime = 20;
		this.gravity = 0f;
		this.hasPhysics = false;
		this.xd = vx * 1;
		this.yd = vy * 1;
		this.zd = vz * 1;
		this.pickSprite(spriteSet);
	}

	@Override
	public int getLightColor(float partialTick) {
		return 15728880;
	}

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.NO_RENDER;
    }

    @Override
    public void tick() {
        super.tick();
    }
}