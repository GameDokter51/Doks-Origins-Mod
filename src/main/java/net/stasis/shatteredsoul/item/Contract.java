
package net.stasis.shatteredsoul.item;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.minecraftforge.registries.ForgeRegistries;
import net.stasis.shatteredsoul.init.ShatteredSoulItems;
import net.stasis.shatteredsoul.init.ShatteredSoulParticles;

public class Contract extends Item {
	public Contract() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.BLOCK;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 20;
	}

	public static void signContract(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		final Minecraft instance = Minecraft.getInstance();

		if (entity == null)
			return;
		{
			Entity _ent = entity;
			if (!_ent.level().isClientSide() && _ent.getServer() != null) {
				_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
						_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "tag @s add shatteredsoul.bound");
			}
		}
		{
			Entity _ent = entity;
			if (!_ent.level().isClientSide() && _ent.getServer() != null) {
				_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
						_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "tag @a remove shatteredsoul.bound");
			}
		}
		if (world.isClientSide())
			instance.gameRenderer.displayItemActivation(itemstack);
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("shatteredsoul:suspense_strike")), SoundSource.MASTER, 1, (float) 0.9);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("shatteredsoul:suspense_strike")), SoundSource.MASTER, 1, (float) 0.9, false);
			}
		}
		itemstack.getOrCreateTag().putString("BoundPlayer", (entity.getDisplayName().getString()));
		if (entity instanceof LivingEntity _entity) {
			ItemStack _setstack = new ItemStack(ShatteredSoulItems.SIGNED_CONTRACT.get()).copy();
			_setstack.setCount(1);
			_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
			if (_entity instanceof Player _player) {
				_player.getInventory().setChanged();
			}
		}
		(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().putString("BoundPlayer", (entity.getDisplayName().getString()));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		signContract(level, player.getX(), player.getY(), player.getZ(), player, stack);


		if (level instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (ShatteredSoulParticles.FOG_SPREAD.get()), player.getX(), player.getY(), player.getZ(), 1, 0, 0, 0, 0);

		return InteractionResultHolder.success(stack);
	}
}