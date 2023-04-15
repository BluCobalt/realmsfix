package dev.blucobalt.rbr.mixin;

import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
    /**
     * don't let if blocks run
     */
    @Inject(method = "getIntVideoOptions", at = @At("RETURN"), cancellable = true)
    public void getIntVideoOptions(GameOptions.Option option, CallbackInfoReturnable<Boolean> cir) {
        if (option == GameOptions.Option.REALMS_NOTIFICATIONS) {
            cir.setReturnValue(false);
        }
    }
}
