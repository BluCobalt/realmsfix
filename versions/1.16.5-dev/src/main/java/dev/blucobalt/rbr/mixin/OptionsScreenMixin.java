package dev.blucobalt.rbr.mixin;

import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.OptionButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin {
    /**
     * don't draw button/do nothing when it tries to add it
     */
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/OptionsScreen;addButton(Lnet/minecraft/client/gui/widget/ClickableWidget;)Lnet/minecraft/client/gui/widget/ClickableWidget;", ordinal = 3))
    public ClickableWidget donothing(OptionsScreen instance, ClickableWidget clickableWidget) {
        return null;
    }
}