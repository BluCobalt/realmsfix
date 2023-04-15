package dev.blucobalt.rbr.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    /**
     * don't let if blocks run
     */
    @Inject(method = "areRealmsNotificationsEnabled", at = @At("RETURN"), cancellable = true)
    public void areRealmsNotificationsEnabled(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    /**
     * don't draw button/do nothing when it tries to add it
     */
    @Redirect(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addButton(Lnet/minecraft/client/gui/widget/ButtonWidget;)Lnet/minecraft/client/gui/widget/ButtonWidget;", ordinal = 2))
    public ButtonWidget donothing(TitleScreen instance, ButtonWidget buttonWidget) {
        // do nothing
        return null;
    }
}
