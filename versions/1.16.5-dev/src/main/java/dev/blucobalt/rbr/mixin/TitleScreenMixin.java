package dev.blucobalt.rbr.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
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
     * it will npe if it's null so just set it invisible
     */
    @Redirect(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addButton(Lnet/minecraft/client/gui/widget/ClickableWidget;)Lnet/minecraft/client/gui/widget/ClickableWidget;", ordinal = 2))
    public ClickableWidget donothing(TitleScreen instance, ClickableWidget clickableWidget) {
        clickableWidget.visible = false;
        return clickableWidget;
    }
}
