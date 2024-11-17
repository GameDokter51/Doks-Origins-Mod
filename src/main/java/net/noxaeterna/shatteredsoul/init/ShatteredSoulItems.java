package net.noxaeterna.shatteredsoul.init;

import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.noxaeterna.shatteredsoul.item.SignedContract;
import net.noxaeterna.shatteredsoul.item.ParticleItem;
import net.noxaeterna.shatteredsoul.item.Contract;
import net.noxaeterna.shatteredsoul.ShatteredSoul;

public class ShatteredSoulItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ShatteredSoul.MODID);
	public static final RegistryObject<Item> CONTRACT = ITEMS.register("contract", () -> new Contract());
	public static final RegistryObject<Item> SIGNED_CONTRACT = ITEMS.register("signed_contract", () -> new SignedContract());

	public static final RegistryObject<Item> PARTICLE_ITEM = ITEMS.register("particle_item", () -> new ParticleItem(new Item.Properties().rarity(Rarity.EPIC)));
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
