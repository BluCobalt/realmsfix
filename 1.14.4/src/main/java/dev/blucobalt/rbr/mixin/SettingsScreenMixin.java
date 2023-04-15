package dev.blucobalt.rbr.mixin;

import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SettingsScreen.class)
public class SettingsScreenMixin {
    /**
     * don't draw button/do nothing when it tries to add it
     */
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/SettingsScreen;addButton(Lnet/minecraft/client/gui/widget/AbstractButtonWidget;)Lnet/minecraft/client/gui/widget/AbstractButtonWidget;", ordinal = 3),
            expect = 0, require = 0)
    public AbstractButtonWidget donothing(SettingsScreen instance, AbstractButtonWidget abstractButtonWidget) {
        // do nothing
        return null;
    }
}
