package dev.blucobalt.rbr.mixin;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(OptionsScreen.class)
public class OptionsScreenMixin {
    /**
     * don't draw button/do nothing when it tries to add it
     * 1.16.5
     */
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/OptionsScreen;addButton(Lnet/minecraft/client/gui/widget/ClickableWidget;)Lnet/minecraft/client/gui/widget/ClickableWidget;", ordinal = 3),
            expect = 0, require = 0)
    public ClickableWidget donothing(OptionsScreen instance, ClickableWidget clickableWidget) {
        return null;
    }

    /**
     * don't draw button/do nothing when it tries to add it
     * 1.17.1-1.18.2
     */
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/OptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 3),
            expect = 0, require = 0)
    public Element donothing(OptionsScreen instance, Element element) {
        return null;
    }
}



