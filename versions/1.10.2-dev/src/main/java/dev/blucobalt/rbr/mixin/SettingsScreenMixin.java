package dev.blucobalt.rbr.mixin;

import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(SettingsScreen.class)
public class SettingsScreenMixin {
    /**
     * don't draw button/do nothing when it tries to add it
     */
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 4))
    public boolean donothing(List<ButtonWidget> instance, Object e) {
        // do nothing
        return false;
    }
}
