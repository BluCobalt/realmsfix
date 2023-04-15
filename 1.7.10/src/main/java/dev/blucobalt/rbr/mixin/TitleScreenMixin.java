package dev.blucobalt.rbr.mixin;


import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Pseudo
@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    /**
     * don't let if blocks run
     * 1.8.9-1.12.2
     */
    @Inject(method = "areRealmsNotificationsEnabled", at = @At("RETURN"), cancellable = true,
            expect = 0, require = 0)
    public void areRealmsNotificationsEnabled(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    /**
     * don't draw button/do nothing when it tries to add it
     * 1.8.9-1.9.4
     */
    @Redirect(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 2),
            expect = 0, require = 0)
    public boolean donothing(List<ButtonWidget> instance, Object e) {
        // do nothing
        return false;
    }

    /**
     * don't draw button/do nothing when it tries to add it
     * 1.7.10
     */
    @Redirect(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 2),
            expect = 0, require = 0)
    public boolean donothing1_7_10(List<ButtonWidget> instance, Object e)
    {
        // do nothing
        return false;
    }

    /**
     * don't draw button/do nothing when it tries to add it
     * 1.10.2-1.12.2
     */
    @Redirect(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addButton(Lnet/minecraft/client/gui/widget/ButtonWidget;)Lnet/minecraft/client/gui/widget/ButtonWidget;", ordinal = 0),
            expect = 0, require = 0)
    public ButtonWidget donothing1_10_2(TitleScreen instance, ButtonWidget buttonWidget) {
        // do nothing
        return null;
    }
}
