package net.noxaeterna.shatteredsoul.client.particle;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;

import org.joml.Math;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;

@OnlyIn(Dist.CLIENT)
public class RayParticle extends TextureSheetParticle {

    private final SpriteSet sprites;
    private static final Quaternionf QUATERNION = new Quaternionf(0F, -0.7F, 0.7F, 0F);

    RayParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites, float size, double velX, double velY, double velZ) {
        super(world, x, y+1, z, 0.0, 0.0, 0.0);
        this.quadSize = (float) 1 - size/2;
        this.setParticleSpeed(0D, 0D, 0D);
        this.lifetime = (int) (32);
        this.sprites = sprites;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float ticks) {

        Vec3 vec3 = camera.getPosition();
        float x = (float) (Mth.lerp(ticks, this.xo, this.x) - vec3.x());
        float y = (float) (Mth.lerp(ticks, this.yo, this.y) - vec3.y());
        float z = (float) (Mth.lerp(ticks, this.zo, this.z) - vec3.z());

        Vector3f[] vector3fn = new Vector3f[]{new Vector3f(-0.1F, 0.0F, 1.0F), new Vector3f(-0.1F, 0.0F, -3.0F), new Vector3f(0.1F, 0.0F, -3.0F), new Vector3f(0.1F, 0.0F, 1.0F)};
        Vector3f[] vector3fs = new Vector3f[]{new Vector3f(0.1F, 0.0F, 1.0F), new Vector3f(0.1F, 0.0F, -3.0F), new Vector3f(-0.1F, 0.0F, -3.0F), new Vector3f(-0.1F, 0.0F, 1.0F)};
        Vector3f[] vector3fe = new Vector3f[]{new Vector3f(0.0F, -0.1F, 1.0F), new Vector3f(0.0F, -0.1F, -3.0F), new Vector3f(0.0F, 0.1F, -3.0F), new Vector3f(0.0F, 0.1F, 1.0F)};
        Vector3f[] vector3fw = new Vector3f[]{new Vector3f(0.0F, 0.1F, 1.0F), new Vector3f(0.0F, 0.1F, -3.0F), new Vector3f(0.0F, -0.1F, -3.0F), new Vector3f(0.0F, -0.1F, 1.0F)};

        float f4 = this.getQuadSize(ticks);

        for (int i = 0; i < 4; ++i) {
            Vector3f vector3f1 = vector3fn[i];
            vector3f1.rotate(QUATERNION);
            vector3f1.mul(f4);
            vector3f1.add(x, y, z);

            Vector3f vector3f2 = vector3fs[i];
            vector3f2.rotate(QUATERNION);
            vector3f2.mul(f4);
            vector3f2.add(x, y, z);
            
            Vector3f vector3f3 = vector3fe[i];
            vector3f3.rotate(QUATERNION);
            vector3f3.mul(f4);
            vector3f3.add(x, y, z);
            
            Vector3f vector3f4 = vector3fw[i];
            vector3f4.rotate(QUATERNION);
            vector3f4.mul(f4);
            vector3f4.add(x, y, z);
        }

        float f7 = this.getU0();
        float f8 = this.getU1();
        float f5 = this.getV0();
        float f6 = this.getV1();
        int light = 15728880;

        buffer.vertex(vector3fn[0].x(), vector3fn[0].y(), vector3fn[0].z()).uv(f8, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fn[1].x(), vector3fn[1].y(), vector3fn[1].z()).uv(f8, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fn[2].x(), vector3fn[2].y(), vector3fn[2].z()).uv(f7, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fn[3].x(), vector3fn[3].y(), vector3fn[3].z()).uv(f7, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        
        buffer.vertex(vector3fs[0].x(), vector3fs[0].y(), vector3fs[0].z()).uv(f8, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fs[1].x(), vector3fs[1].y(), vector3fs[1].z()).uv(f8, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fs[2].x(), vector3fs[2].y(), vector3fs[2].z()).uv(f7, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fs[3].x(), vector3fs[3].y(), vector3fs[3].z()).uv(f7, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();

        buffer.vertex(vector3fe[0].x(), vector3fe[0].y(), vector3fe[0].z()).uv(f8, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fe[1].x(), vector3fe[1].y(), vector3fe[1].z()).uv(f8, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fe[2].x(), vector3fe[2].y(), vector3fe[2].z()).uv(f7, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fe[3].x(), vector3fe[3].y(), vector3fe[3].z()).uv(f7, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();

        buffer.vertex(vector3fw[0].x(), vector3fw[0].y(), vector3fw[0].z()).uv(f8, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fw[1].x(), vector3fw[1].y(), vector3fw[1].z()).uv(f8, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fw[2].x(), vector3fw[2].y(), vector3fw[2].z()).uv(f7, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        buffer.vertex(vector3fw[3].x(), vector3fw[3].y(), vector3fw[3].z()).uv(f7, f6).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
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
            return new RayParticle(level, x, y, z, this.sprites, ((float)Math.random()), dx, dy, dz);
        }
    }

}