/**
 * This file is part of realmsfix.
 *
 * realmsfix is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * realmsfix is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with realmsfix.  If not, see <http://www.gnu.org/licenses/>.
 */
package dev.blucobalt.realmsfix.mixin;


import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.realms.gui.screen.RealmsNotificationsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(RealmsNotificationsScreen.class)
public class RealmsNotificationsScreen1_20_4
{
    @Inject(method = "render", at = @org.spongepowered.asm.mixin.injection.At(value = "HEAD"), cancellable = true)
    public void donothing(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        ci.cancel();
    }
}
