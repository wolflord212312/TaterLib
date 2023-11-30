package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.TaterLibCommand;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.logger.AbstractLogger;

/** Main class for the plugin. */
public class TaterLib {
    private static final TaterLib instance = new TaterLib();
    private static boolean STARTED = false;
    private static boolean RELOADED = false;
    private Object plugin;
    private AbstractLogger logger;

    /**
     * Getter for the singleton instance of the class.
     *
     * @return The singleton instance
     */
    public static TaterLib getInstance() {
        return instance;
    }

    /**
     * Get the plugin
     *
     * @return The plugin
     */
    public static Object getPlugin() {
        return instance.plugin;
    }

    /**
     * Set the plugin
     *
     * @param plugin The plugin
     */
    private static void setPlugin(Object plugin) {
        instance.plugin = plugin;
    }

    /**
     * Get the logger
     *
     * @return The logger
     */
    public static AbstractLogger getLogger() {
        return instance.logger;
    }

    /**
     * Set the logger
     *
     * @param logger The logger
     */
    private static void setLogger(AbstractLogger logger) {
        instance.logger = logger;
    }

    /**
     * Start
     *
     * @param plugin The plugin
     * @param logger The logger
     */
    public static void start(Object plugin, AbstractLogger logger) {
        setPlugin(plugin);
        setLogger(logger);

        if (STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            TaterAPI api = TaterAPIProvider.get();

            // Register TaterLib hook
            TaterAPIProvider.addHook("taterlib", new Object());

            // Register hooks
            ServerEvents.STARTED.register(
                    event -> {
                        // Register LuckPerms hook
                        if (api.isPluginModLoaded("LuckPerms")) {
                            instance.logger.info("LuckPerms detected, enabling LuckPerms hook.");
                            TaterAPIProvider.addHook("luckperms", new LuckPermsHook());
                        }
                    });

            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    (event -> event.registerCommand(TaterLib.getPlugin(), new TaterLibCommand())));
        }

        instance.logger.info(Constants.PROJECT_NAME + " has been started!");
    }

    /** Start */
    public static void start() {
        start(instance.plugin, instance.logger);
    }

    /** Stop */
    public static void stop() {
        if (!STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has already stopped!");
            return;
        }
        STARTED = false;

        instance.logger.info(Constants.PROJECT_NAME + " has been stopped!");
        TaterAPIProvider.unregister();
    }

    /** Reload */
    public static void reload() {
        if (!STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Start
        start();

        instance.logger.info(Constants.PROJECT_NAME + " has been reloaded!");
    }

    /** Constants used throughout the plugin. */
    public static class Constants {
        public static final String PROJECT_NAME = "TaterLib";
        public static final String PROJECT_ID = "taterlib";
        public static final String PROJECT_VERSION = "1.1.0-R0.14-SNAPSHOT";
        public static final String PROJECT_AUTHORS = "p0t4t0sandwich";
        public static final String PROJECT_DESCRIPTION =
                "A cross API code library for various generalizations used in the Tater* plugins";
        public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterLib";
    }
}