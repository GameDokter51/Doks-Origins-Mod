package net.noxaeterna.shatteredsoul.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mod.maxbogomol.fluffy_fur.FluffyFur;
import mod.maxbogomol.fluffy_fur.client.event.ClientTickHandler;
import mod.maxbogomol.fluffy_fur.client.render.RenderBuilder;
import mod.maxbogomol.fluffy_fur.common.item.IGuiParticleItem;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import mod.maxbogomol.fluffy_fur.util.RenderUtil;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.noxaeterna.shatteredsoul.init.ShatteredSoulParticles;

public class ParticleItem extends Item implements IGuiParticleItem {

    public ParticleItem(Properties rarity) {
        super(rarity);
    }

    @Override
    public void renderParticle(PoseStack poseStack, LivingEntity entity, Level level, ItemStack stack, int x, int y, int seed, int guiOffset) {
        float ticks = ClientTickHandler.getTotal() * 0.5f;

        poseStack.pushPose();
        poseStack.translate(x + 8, y + 8, 100);
        poseStack.mulPose(Axis.ZP.rotationDegrees(ticks));
        RenderBuilder builder = RenderBuilder.create().setRenderType(FluffyFurRenderTypes.ADDITIVE_TEXTURE);
        builder.setUV(RenderUtil.getSprite(FluffyFur.MOD_ID, "particle/star"));
        builder.setColorRaw(0.1f, 0.1f, 1).setAlpha(0.7f)
                .renderCenteredQuad(poseStack, 10f);
        poseStack.mulPose(Axis.ZP.rotationDegrees(25.5f));
        builder.setColorRaw(0, 0.4f, 1).setAlpha(1f)
                .renderCenteredQuad(poseStack, 10f)
                .endBatch();
        poseStack.popPose();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
		if (level instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (ShatteredSoulParticles.FOG_SPREAD.get()), player.getX(), player.getY(), player.getZ(), 1, 0, 0, 0, 0);
        return InteractionResultHolder.success(stack);
    }
}