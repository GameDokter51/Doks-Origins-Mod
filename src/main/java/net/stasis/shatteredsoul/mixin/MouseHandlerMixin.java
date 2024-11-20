package net.stasis.shatteredsoul.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.KeyMapping;
import net.stasis.shatteredsoul.ShatteredSoul;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public abstract class MouseHandlerMixin {
    private Minecraft Instance = Minecraft.getInstance();

    @Inject(method = "onPress", at = @At("HEAD"), cancellable = true)
    private void onPress(long window, int button, int action, int mods, CallbackInfo callbackInfo) {
        if (ShatteredSoul.isLocked(Instance.player) && !Instance.isPaused()) {
            KeyMapping.releaseAll();
            callbackInfo.cancel();
        }
    }

    @Inject(method = "onScroll", at = @At("HEAD"), cancellable = true)
    private void onScroll(long window, double horizontal, double vertical, CallbackInfo callbackInfo) {
        if (ShatteredSoul.isLocked(Instance.player) && !Instance.isPaused()) {
            KeyMapping.releaseAll();
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = "onMove", at = @At("HEAD"), cancellable = true)
    private void onMove(long window, double xpos, double ypos, CallbackInfo callbackInfo) {
        if (ShatteredSoul.isLocked(Instance.player) && !Instance.isPaused()) {
            callbackInfo.cancel();
        }
    }
}