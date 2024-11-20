package net.stasis.shatteredsoul.item;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.network.chat.Component;

import net.stasis.shatteredsoul.init.ShatteredSoulItems;
@Mod.EventBusSubscriber
public class ContractTooltip {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
        if (event.getToolTip() == null)
            return;
        if (event.getItemStack().getItem() == ShatteredSoulItems.SIGNED_CONTRACT.get()) {
            event.getToolTip().add(Component.literal(("\u00A73Signed by " + event.getItemStack().getOrCreateTag().getString("BoundPlayer"))));
            event.getToolTip().add(Component.literal("\u00A70\u00A7kTheir soul is now forfeit"));
        }
	}
}
