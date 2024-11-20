package net.stasis.shatteredsoul.mixin;

import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.stasis.shatteredsoul.ShatteredSoul;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public abstract class KeyboardHandlerMixin {
    private Minecraft Instance = Minecraft.getInstance();
    
    @Inject(method = "keyPress", at = @At("HEAD"), cancellable = true)
    public void keyPress(long window, int key, int scancode, int action, int modifiers, CallbackInfo callbackInfo) {
        if (ShatteredSoul.isLocked(Instance.player) && !Instance.isPaused()) {
            KeyMapping.releaseAll();
            callbackInfo.cancel();
        }
    }
}