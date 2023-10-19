package dev.neuralnexus.taterlib.velocity.listeners.pluginmessages;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import dev.neuralnexus.taterlib.common.listeners.pluginmessages.PluginMessageListener;
import dev.neuralnexus.taterlib.velocity.abstractions.player.VelocityPlayer;

public class VelocityPluginMessageListener {
    /**
     * Called when a plugin message is received.
     * @param event The event.
     */
    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        PluginMessageListener.onPluginMessage(event.getIdentifier().getId(), event.getData());
        if (event.getSource() instanceof ServerConnection) {
            PluginMessageListener.onServerPluginMessage(event.getIdentifier().getId(), event.getData());
        } else if (event.getSource() instanceof Player){
            PluginMessageListener.onPlayerPluginMessage(event.getIdentifier().getId(), new VelocityPlayer((Player) event.getSource()), event.getData());
        }
    }
}