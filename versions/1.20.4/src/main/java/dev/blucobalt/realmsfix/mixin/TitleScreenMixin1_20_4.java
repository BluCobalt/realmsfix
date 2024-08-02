/**
 * This file is part of realmsfix.
 * <p>
 * realmsfix is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 * <p>
 * realmsfix is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with realmsfix.  If not, see <http://www.gnu.org/licenses/>.
 */
package dev.blucobalt.realmsfix.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Pseudo
@Mixin(TitleScreen.class)
public class TitleScreenMixin1_20_4
{
    @Inject(method = "initWidgetsNormal", at = @At(value = "TAIL"))
    public void donothing(int y, int spacingY, CallbackInfo ci)
    {
        if (MinecraftClient.getInstance().currentScreen instanceof TitleScreen){
            TitleScreenAccessor1_20_4 ts = (TitleScreenAccessor1_20_4) MinecraftClient.getInstance().currentScreen;
            List<Drawable> drawables = ts.getDrawables();
            for (Drawable drawable : drawables) {
                if (drawable instanceof ButtonWidget button) {
                    Text message = button.getMessage();
                    MutableText t = (MutableText) message;

                    try {
                        TranslatableTextAccessor1_20_4 accessor = (TranslatableTextAccessor1_20_4) t.getContent();
                        if (accessor.getKey().equals("menu.online")) {
                            ((ButtonWidget) drawable).visible = false;
                        }
                    } catch (ClassCastException ignored)
                    {}
                }
            }
        }
    }
}
