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
package dev.blucobalt.realmsfix;


import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

import java.util.*;


public class Entrypoint
    implements PreLaunchEntrypoint
{
    private static final Logger              LOGGER      = LogManager.getLogger("realmsfix");
    private static final List<String>      versions      = new ArrayList<>();

    static {
        versions.add("1.7.10");
        versions.add("1.8.9");
        versions.add("1.9.4");
        versions.add("1.10.2");
        versions.add("1.11.2");
        versions.add("1.12.2");
        versions.add("1.13.2");
        versions.add("1.14.4");
        versions.add("1.15.2");
        versions.add("1.16.5");
        versions.add("1.17.1");
        versions.add("1.18.2");
        versions.add("1.19.4");
        versions.add("1.20.4");
        versions.add("1.21");
        versions.add("1.21.2");
    }

    @Override
    public void onPreLaunch()
    {
        @SuppressWarnings("OptionalGetWithoutIsPresent") // minecraft is always going to be present
        final String version = FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion().getFriendlyString();

        long versionNumber = getVersionNumber(version);
        String computedVersion = "0";
        for (String available: versions)
        {
            long availableNumber = getVersionNumber(available);

            if (availableNumber == versionNumber)
            {
                computedVersion = available;
                break;
            }
            if (availableNumber < versionNumber)
            {
                computedVersion = available;
            }
        }

        if (computedVersion.equals("0"))
        {
            LOGGER.error("Could not find compatible version for version {}. Check for update maybe?", version);
            return;
        }

        LOGGER.info("resolved config: realmsfix-{}.mixins.json", computedVersion);
        Mixins.addConfiguration("realmsfix-" + computedVersion + ".mixins.json");
    }

    private static long getVersionNumber(String version)
    {
        String[] parts = version.split("\\.");
        long number = 0;
        for (int i = 0; i < parts.length; i++) {
            number += (long) (Long.parseLong(parts[i]) * Math.pow(100, parts.length - i - 1));
        }
        if (parts.length < 3) {
            // if the version is like 1.21, we assume the patch version is 0
            number *= 100;
        }
        return number;
    }
}
