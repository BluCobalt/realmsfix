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
package dev.blucobalt.realmsfix;


import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.SemanticVersion;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;


public class Entrypoint
    implements PreLaunchEntrypoint
{
    @Override
    public void onPreLaunch()
    {
        @SuppressWarnings("OptionalGetWithoutIsPresent") // minecraft is always going to be present
        String version = FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion().getFriendlyString();

        // all point versions of the latest will be supported, but only the final point release will be supported for older major versions
        if (version.contains("1.20"))
        {
            version = "1.20.4";
        }

        System.out.println("resolved config: " + "realmsfix-" + version + ".mixins.json");
        Mixins.addConfiguration("realmsfix-" + version + ".mixins.json");
    }
}
