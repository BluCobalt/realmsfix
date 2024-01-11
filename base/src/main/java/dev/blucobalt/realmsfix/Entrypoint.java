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


import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

import java.util.HashMap;


public class Entrypoint
    implements PreLaunchEntrypoint
{
    private static final HashMap<String, String> VERSION_MAP;
    private static final Logger LOGGER = LogManager.getLogger("realmsfix");

    static {
        VERSION_MAP = new HashMap<>();
        VERSION_MAP.put("1.20", "1.20.4");
        VERSION_MAP.put("1.19", "1.19.4");
        VERSION_MAP.put("1.18", "1.18.2");
        VERSION_MAP.put("1.17", "1.17.1");
        VERSION_MAP.put("1.16", "1.16.5");
        VERSION_MAP.put("1.15", "1.15.2");
        VERSION_MAP.put("1.14", "1.14.4");
        VERSION_MAP.put("1.13", "1.13.2");
        VERSION_MAP.put("1.12", "1.12.2");
        VERSION_MAP.put("1.11", "1.11.2");
        VERSION_MAP.put("1.10", "1.10.2");
        VERSION_MAP.put("1.9", "1.9.4");
        VERSION_MAP.put("1.8", "1.8.9");
        VERSION_MAP.put("1.7", "1.7.10");
    }

    private static String computedVersion;

    @Override
    public void onPreLaunch()
    {
        @SuppressWarnings("OptionalGetWithoutIsPresent") // minecraft is always going to be present
        final String version = FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion().getFriendlyString();

        VERSION_MAP.forEach((major, point) -> {
            if (version.startsWith(major)) {
                computedVersion = point;
            }
        });

        LOGGER.info("resolved config: " + "realmsfix-" + computedVersion + ".mixins.json");
        Mixins.addConfiguration("realmsfix-" + computedVersion + ".mixins.json");
    }
}
