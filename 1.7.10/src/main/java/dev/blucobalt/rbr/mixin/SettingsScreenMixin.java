package dev.blucobalt.rbr.mixin;

import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Pseudo
@Mixin(SettingsScreen.class)
public class SettingsScreenMixin {
    /**
     * don't draw button/do nothing when it tries to add it
     * 1.8.9-1.12.2
     */
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 4),
            expect = 0, require = 0)
    public boolean donothing(List<ButtonWidget> instance, Object e) {
        // do nothing
        return false;
    }

//    /**
//     * don't draw button/do nothing when it tries to add it
//     */
//    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/SettingsScreen;addButton(Lnet/minecraft/client/gui/widget/ButtonWidget;)Lnet/minecraft/client/gui/widget/ButtonWidget;", ordinal = 4))
//    public ButtonWidget donothing(SettingsScreen instance, ButtonWidget buttonWidget) {
//        // do nothing
//        return null;
//    }
}
