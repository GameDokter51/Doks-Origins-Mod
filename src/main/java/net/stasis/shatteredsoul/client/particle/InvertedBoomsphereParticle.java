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
import mod.maxbogomol.fluffy_fur.client.particle.behavior.SphereParticleBehavior;
import mod.maxbogomol.fluffy_fur.client.particle.data.ColorParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.GenericParticleData;
import mod.maxbogomol.fluffy_fur.common.easing.Easing;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurParticles;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import net.minecraft.client.multiplayer.ClientLevel;

@OnlyIn(Dist.CLIENT)
public class InvertedBoomsphereParticle extends TextureSheetParticle {
	public static InvertedBoomsphereParticleProvider provider(SpriteSet spriteSet) {
		return new InvertedBoomsphereParticleProvider(spriteSet);
	}

	public static class InvertedBoomsphereParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public InvertedBoomsphereParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			InvertedBoomsphereParticle particle = new InvertedBoomsphereParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
			
		    if (level.isClientSide()) {
				
            	ParticleBuilder.create(FluffyFurParticles.SQUARE)
                    .setRenderType(FluffyFurRenderTypes.ADDITIVE_PARTICLE_TEXTURE)
                    .setBehavior(SphereParticleBehavior.create().build())
                    .setColorData(ColorParticleData.create(0, 1, 0, 0, 0.5f, 1).build())
                    .setTransparencyData(GenericParticleData.create(0, (float) zSpeed/4).setEasing(Easing.QUARTIC_IN).build())
                    .setScaleData(GenericParticleData.create((float) ySpeed, 0).setEasing(Easing.QUARTIC_IN).build())
                    .setLifetime((int) Math.round(xSpeed*20))
                    .repeat(level, x, y, z, 1);
				}
			return particle;
		}
	}

	@SuppressWarnings("unused")
	private final SpriteSet spriteSet;

	protected InvertedBoomsphereParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
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