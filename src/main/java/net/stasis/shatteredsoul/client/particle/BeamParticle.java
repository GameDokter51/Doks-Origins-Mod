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
import mod.maxbogomol.fluffy_fur.client.particle.data.SpinParticleData;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurParticles;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import net.minecraft.client.multiplayer.ClientLevel;

@OnlyIn(Dist.CLIENT)
public class BeamParticle extends TextureSheetParticle {
	public static BeamParticleProvider provider(SpriteSet spriteSet) {
		return new BeamParticleProvider(spriteSet);
	}

	public static class BeamParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public BeamParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			BeamParticle particle = new BeamParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
			
		    if (level.isClientSide()) {
				final float xDiff= (float) (x - xSpeed);
				final float yDiff= (float) (y - ySpeed);
				final float zDiff= (float) (z - zSpeed);
				final float dist = (float) Math.sqrt(xDiff*xDiff + yDiff*yDiff + zDiff*zDiff);
				final float yRot = (float) Math.toDegrees(Math.atan2(xDiff, zDiff));
				final float xRot = (float) Math.toDegrees(Math.atan2(yDiff, dist));
				
				ParticleBuilder.create(FluffyFurParticles.DOT)
					.setRenderType(FluffyFurRenderTypes.ADDITIVE_PARTICLE)
					.setBehavior(ParticleBehavior.create()
						.setXSpinData(SpinParticleData.create().setSpinOffsetDegrees(0).build())
						.setYSpinData(SpinParticleData.create().setSpinOffsetDegrees(yRot-90).build())
						.setZSpinData(SpinParticleData.create().setSpinOffsetDegrees(xRot).build())
						.build())
					.setColorData(ColorParticleData.create(0, 1f, 1f, 0f, 1f, 1f).build())
					.setTransparencyData(GenericParticleData.create(0.05f,0).build())
					.setScaleData(GenericParticleData.create(0.5f).build())
					.setLifetime(10)
					.repeat(level, x, y, z, 1);

				ParticleBuilder.create(FluffyFurParticles.DOT)
					.setRenderType(FluffyFurRenderTypes.ADDITIVE_PARTICLE)
					.setBehavior(ParticleBehavior.create()
						.setXSpinData(SpinParticleData.create().setSpinOffsetDegrees(0).build())
						.setYSpinData(SpinParticleData.create().setSpinOffsetDegrees(yRot-90).build())
						.setZSpinData(SpinParticleData.create().setSpinOffsetDegrees(xRot).build())
						.build())
					.setColorData(ColorParticleData.create(0, 1f, 1f, 0f, 1f, 1f).build())
					.setTransparencyData(GenericParticleData.create(0.05f,0).build())
					.setScaleData(GenericParticleData.create(0.5f).build())
					.setLifetime(10)
					.repeat(level, x, y, z, 1);
			}
			return particle;
		}
	}

	@SuppressWarnings("unused")
	private final SpriteSet spriteSet;

	protected BeamParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
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