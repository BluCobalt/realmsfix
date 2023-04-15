package dev.blucobalt.rbr.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;


@Mixin(TitleScreen.class)
public class TitleScreenMixin
{
    /**
     * don't draw button/do nothing when it tries to add it
     */
    @Redirect(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 2), expect = 0, require = 0)
    public boolean donothing(List<ButtonWidget> instance, Object e) {
        // do nothing
        return false;
    }
}
