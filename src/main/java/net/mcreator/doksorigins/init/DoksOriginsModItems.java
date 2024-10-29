
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.doksorigins.init;

import net.mcreator.doksorigins.item.ParticleItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.doksorigins.item.SignedContractItem;
import net.mcreator.doksorigins.item.ContractItem;
import net.mcreator.doksorigins.DoksOriginsMod;

public class DoksOriginsModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DoksOriginsMod.MODID);
	public static final RegistryObject<Item> CONTRACT = ITEMS.register("contract", () -> new ContractItem());
	public static final RegistryObject<Item> SIGNED_CONTRACT = ITEMS.register("signed_contract", () -> new SignedContractItem());

	public static final RegistryObject<Item> PARTICLE_ITEM = ITEMS.register("particle_item", () -> new ParticleItem(new Item.Properties().rarity(Rarity.EPIC)));
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
