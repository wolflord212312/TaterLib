package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginDisableEvent;
import dev.neuralnexus.taterlib.logger.AbstractLogger;
import dev.neuralnexus.taterlib.plugin.Plugin;

public interface TaterLibPlugin extends Plugin {
    /** Start the plugin. */
    default void pluginStart(Object plugin, AbstractLogger logger) {
        logger.info(
                TaterLib.Constants.PROJECT_NAME
                        + " is running on "
                        + TaterAPIProvider.get().serverType()
                        + " "
                        + TaterAPIProvider.get().minecraftVersion()
                        + "!");
        TaterLib.start(plugin, logger);
    }

    /** Stop the plugin. */
    default void pluginStop() {
        TaterLib.stop();
        TaterLib.getLogger().info(TaterLib.Constants.PROJECT_NAME + " has been disabled!");
        PluginEvents.DISABLED.invoke(new CommonPluginDisableEvent());
    }
}