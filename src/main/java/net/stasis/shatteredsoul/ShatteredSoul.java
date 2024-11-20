package net.stasis.shatteredsoul;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.FriendlyByteBuf;

import net.stasis.shatteredsoul.init.ShatteredSoulSounds;
import net.stasis.shatteredsoul.init.ShatteredSoulParticles;
import net.stasis.shatteredsoul.init.ShatteredSoulEffects;
import net.stasis.shatteredsoul.init.ShatteredSoulTabs;
import net.stasis.shatteredsoul.init.ShatteredSoulItems;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.AbstractMap;

@Mod("shatteredsoul")
public class ShatteredSoul {
	public static final Logger LOGGER = LogManager.getLogger(ShatteredSoul.class);
	public static final String MODID = "shatteredsoul";

	public ShatteredSoul() {
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ShatteredSoulSounds.REGISTRY.register(bus);
		ShatteredSoulEffects.REGISTRY.register(bus);
		ShatteredSoulParticles.REGISTRY.register(bus);
		ShatteredSoulTabs.REGISTRY.register(bus);
		ShatteredSoulItems.ITEMS.register(bus);
	}

    public static boolean isLocked(@Nullable LivingEntity entity) {
        return entity != null &&
                 entity.hasEffect(ShatteredSoulEffects.LOCK.get()); //&&
                // !entity.isSpectator() &&
                // !(entity instanceof Player player && player.isCreative());
    }

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
			workQueue.forEach(work -> {
				work.setValue(work.getValue() - 1);
				if (work.getValue() == 0)
					actions.add(work);
			});
			actions.forEach(e -> e.getKey().run());
			workQueue.removeAll(actions);
		}
	}
}
