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
import mod.maxbogomol.fluffy_fur.client.particle.behavior.CubeParticleBehavior;
import mod.maxbogomol.fluffy_fur.client.particle.data.ColorParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.GenericParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.SpinParticleData;
import mod.maxbogomol.fluffy_fur.common.easing.Easing;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import net.minecraft.client.multiplayer.ClientLevel;

import net.stasis.shatteredsoul.init.ShatteredSoulParticles;

@OnlyIn(Dist.CLIENT)
public class CrystalParticle extends TextureSheetParticle {
	public static CrystalParticleProvider provider(SpriteSet spriteSet) {
		return new CrystalParticleProvider(spriteSet);
	}

	public static class CrystalParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public CrystalParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			CrystalParticle particle = new CrystalParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
			
		    if (level.isClientSide()) {
				
				ParticleBuilder.create(ShatteredSoulParticles.CRYSTAL_BASE)
						.setRenderType(FluffyFurRenderTypes.TRANSLUCENT_PARTICLE_TEXTURE)
						.setBehavior(CubeParticleBehavior.create().enableSided()
							.setXSpinData(SpinParticleData.create().setSpinOffsetDegrees(30).build())
							.setYSpinData(SpinParticleData.create().setSpinOffsetDegrees(20).build())
							.setZSpinData(SpinParticleData.create().setSpinOffsetDegrees(20).build())
							.build())
						.setColorData(ColorParticleData.create(1, 1, 1, 1, 1, 1).build())
						.setTransparencyData(GenericParticleData.create(1).build())
						.setScaleData(GenericParticleData.create(0.5f).build())
						.setLifetime(8)
						.setVelocity(0, 0, 0)
						.repeat(level, x, y, z, 1);
	
				ParticleBuilder.create(ShatteredSoulParticles.CRYSTAL_BASE)
						.setRenderType(FluffyFurRenderTypes.TRANSLUCENT_PARTICLE_TEXTURE)
						.setBehavior(CubeParticleBehavior.create().enableSided()
							.setXSpinData(SpinParticleData.create().setSpinOffsetDegrees(45).build())
							.setYSpinData(SpinParticleData.create().setSpinOffsetDegrees(50).build())
							.setZSpinData(SpinParticleData.create().setSpinOffsetDegrees(45).build())
							.build())
						.setColorData(ColorParticleData.create(1, 1, 1, 1, 1, 1).build())
						.setTransparencyData(GenericParticleData.create(1).build())
						.setScaleData(GenericParticleData.create(0.5f).build())
						.setLifetime(8)
						.setVelocity(0, 0, 0)
						.repeat(level, x, y, z, 1);
	
				ParticleBuilder.create(ShatteredSoulParticles.CRYSTAL_BASE)
						.setRenderType(FluffyFurRenderTypes.ADDITIVE_PARTICLE_TEXTURE)
						.setBehavior(CubeParticleBehavior.create().enableSided()
							.setXSpinData(SpinParticleData.create().setSpinOffsetDegrees(30).build())
							.setYSpinData(SpinParticleData.create().setSpinOffsetDegrees(20).build())
							.setZSpinData(SpinParticleData.create().setSpinOffsetDegrees(20).build())
							.build())
						.setColorData(ColorParticleData.create(1, 1, 1, 1, 1, 1).build())
						.setTransparencyData(GenericParticleData.create(0.3f).build())
						.setScaleData(GenericParticleData.create(0.51f, 0.54f, 0.51f).setEasing(Easing.SINE_IN_OUT).build())
						.setLifetime(8)
						.setVelocity(0, 0, 0)
						.repeat(level, x, y, z, 1);

				ParticleBuilder.create(ShatteredSoulParticles.CRYSTAL_BASE)
						.setRenderType(FluffyFurRenderTypes.ADDITIVE_PARTICLE_TEXTURE)
						.setBehavior(CubeParticleBehavior.create().enableSided()
							.setXSpinData(SpinParticleData.create().setSpinOffsetDegrees(45).build())
							.setYSpinData(SpinParticleData.create().setSpinOffsetDegrees(50).build())
							.setZSpinData(SpinParticleData.create().setSpinOffsetDegrees(45).build())
							.build())
						.setColorData(ColorParticleData.create(1, 1, 1, 1, 1, 1).build())
						.setTransparencyData(GenericParticleData.create(0.3f).build())
						.setScaleData(GenericParticleData.create(0.54f, 0.51f, 0.54f).setEasing(Easing.SINE_IN_OUT).build())
						.setLifetime(8)
						.setVelocity(0, 0, 0)
						.repeat(level, x, y, z, 1);
				}
			return particle;
		}
	}

	@SuppressWarnings("unused")
	private final SpriteSet spriteSet;

	protected CrystalParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
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