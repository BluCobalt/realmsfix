package dev.blucobalt.rbr.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    /**
     * don't let if blocks run
     * 1.14.4-1.15.2
     */
    @Inject(method = "areRealmsNotificationsEnabled", at = @At("RETURN"), cancellable = true,
            expect = 0, require = 0)
    public void areRealmsNotificationsEnabled(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    /**
     * don't draw button/do nothing when it tries to add it
     */
    @Redirect(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addButton(Lnet/minecraft/client/gui/widget/AbstractButtonWidget;)Lnet/minecraft/client/gui/widget/AbstractButtonWidget;", ordinal = 2),
            expect = 0, require = 0)
    public AbstractButtonWidget donothing(TitleScreen instance, AbstractButtonWidget abstractButtonWidget) {
        // do nothing
        return null;
    }

    /**
     * don't draw button/do nothing when it tries to add it
     */
    @Redirect(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addButton(Lnet/minecraft/client/gui/widget/AbstractButtonWidget;)Lnet/minecraft/client/gui/widget/AbstractButtonWidget;", ordinal = 2),
            expect = 0, require = 0)
    public AbstractButtonWidget donothing1_15_2(TitleScreen instance, AbstractButtonWidget abstractButtonWidget) {
        // do nothing
        return null;
    }
}
