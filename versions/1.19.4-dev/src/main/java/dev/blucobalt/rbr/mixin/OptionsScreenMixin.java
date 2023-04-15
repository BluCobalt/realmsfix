package dev.blucobalt.rbr.mixin;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin {
//    /**
//     * don't draw button/do nothing when it tries to add it
//     */
//    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/OptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 3))
//    public Element donothing(OptionsScreen instance, Element element) {
//        return null;
//    }
}