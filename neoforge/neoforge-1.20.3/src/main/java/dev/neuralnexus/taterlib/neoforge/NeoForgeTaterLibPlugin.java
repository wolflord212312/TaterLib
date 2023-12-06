package dev.neuralnexus.taterlib.neoforge;

import com.mojang.logging.LogUtils;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.api.PluginMessageEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.neoforge.event.pluginmessage.NeoForgeRegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.neoforge.hooks.permissions.NeoForgePermissionsHook;
import dev.neuralnexus.taterlib.neoforge.listeners.block.NeoForgeBlockListener;
import dev.neuralnexus.taterlib.neoforge.listeners.command.NeoForgeCommandsListener;
import dev.neuralnexus.taterlib.neoforge.listeners.entity.NeoForgeEntityListener;
import dev.neuralnexus.taterlib.neoforge.listeners.player.NeoForgePlayerListener;
import dev.neuralnexus.taterlib.neoforge.listeners.server.NeoForgeServerListener;
import dev.neuralnexus.taterlib.neoforge.networking.ModMessages;
import dev.neuralnexus.taterlib.neoforge.server.NeoForgeServer;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

/** NeoForge entry point. */
@Mod(TaterLib.Constants.PROJECT_ID)
public class NeoForgeTaterLibPlugin implements TaterLibPlugin {
    public NeoForgeTaterLibPlugin() {
        TaterAPIProvider.register(FMLLoader.versionInfo().mcVersion());
        TaterAPIProvider.addHook(new NeoForgePermissionsHook());
        pluginStart(this, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, LogUtils.getLogger()));
        TaterAPI api = TaterAPIProvider.get(ServerType.NEOFORGE);
        api.setIsModLoaded(ModList.get()::isLoaded);
        api.setServer(() -> new NeoForgeServer(ServerLifecycleHooks.getCurrentServer()));

        // Register listeners
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new NeoForgeBlockListener());
        NeoForge.EVENT_BUS.register(new NeoForgeCommandsListener());
        NeoForge.EVENT_BUS.register(new NeoForgeEntityListener());
        NeoForge.EVENT_BUS.register(new NeoForgePlayerListener());
        NeoForge.EVENT_BUS.register(new NeoForgeServerListener());

        // Register plugin channels
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
    }

    /**
     * Called when CommonSetupEvent is fired.
     *
     * @param event The event.
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());
        PluginMessageEvents.REGISTER_PLUGIN_MESSAGES.invoke(
                new NeoForgeRegisterPluginMessagesEvent());
        ModMessages.register();
        ModMessages.clearQueue();
    }

    /**
     * Called when the server is stopping.
     *
     * @param event The event.
     */
    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        pluginStop();
    }
}
