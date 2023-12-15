package dev.neuralnexus.taterlib.bungee;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.bungee.event.command.BungeeCommandRegisterEvent;
import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStartedEvent;
import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStartingEvent;
import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStoppedEvent;
import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStoppingEvent;
import dev.neuralnexus.taterlib.bungee.hooks.permissions.BungeePermissionsHook;
import dev.neuralnexus.taterlib.bungee.listeners.player.BungeePlayerListener;
import dev.neuralnexus.taterlib.bungee.listeners.pluginmessages.BungeePluginMessageListener;
import dev.neuralnexus.taterlib.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

/** Bungee entry point. */
public class BungeeTaterLibPlugin extends Plugin implements TaterLibPlugin {
    private static ProxyServer proxyServer;

    public BungeeTaterLibPlugin() {
        TaterAPIProvider.register(ProxyServer.getInstance().getVersion());
        TaterAPIProvider.addHook(new BungeePermissionsHook());
        pluginStart(
                this,
                new LoggerAdapter(
                        TaterLib.Constants.PROJECT_ID, ProxyServer.getInstance().getLogger()));
        TaterAPI api = TaterAPIProvider.get(ServerType.BUNGEECORD);
        api.setIsPluginLoaded(
                (plugin) -> ProxyServer.getInstance().getPluginManager().getPlugin(plugin) != null);
        api.setServer(() -> new BungeeProxyServer(ProxyServer.getInstance()));
    }

    /**
     * Get the proxy server.
     *
     * @return The proxy server.
     */
    public static ProxyServer getProxyServer() {
        return proxyServer;
    }

    @Override
    public void onEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());
        proxyServer = ProxyServer.getInstance();

        // Register listeners
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        Utils.runTaskLaterAsync(
                () -> CommandEvents.REGISTER_COMMAND.invoke(new BungeeCommandRegisterEvent()),
                5000L);
        pluginManager.registerListener(this, new BungeePlayerListener());
        pluginManager.registerListener(this, new BungeePluginMessageListener());
        ServerEvents.STARTING.invoke(new BungeeServerStartingEvent());
        Utils.runTaskLaterAsync(
                () -> ServerEvents.STARTED.invoke(new BungeeServerStartedEvent()), 5000L);
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BungeeServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BungeeServerStoppedEvent());
        pluginStop();
    }
}