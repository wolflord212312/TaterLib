package dev.neuralnexus.taterlib.sponge.listeners.player;

import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import dev.neuralnexus.taterlib.sponge.abstractions.events.player.*;
import org.spongepowered.api.advancement.DisplayInfo;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.advancement.AdvancementEvent;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.entity.living.humanoid.player.RespawnPlayerEvent;
import org.spongepowered.api.event.filter.cause.All;
import org.spongepowered.api.event.message.MessageEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;

/**
 * Listens to player events.
 */
public class SpongePlayerListener {
    /**
     * Called when a player progresses in an advancement.
     * @param event The event.
     */
    @Listener
    public void onPlayerAdvancement(AdvancementEvent.Grant event) {
        DisplayInfo display = event.getAdvancement().getDisplayInfo().orElse(null);
        if (display != null && display.doesAnnounceToChat()) {
            PlayerListener.onPlayerAdvancementFinished(new SpongePlayerAdvancementEvent.SpongePlayerAdvancementFinishedEvent(event));
        } else {
            PlayerListener.onPlayerAdvancementProgress(new SpongePlayerAdvancementEvent.SpongePlayerAdvancementProgressEvent(event));
        }
    }

    /**
     * Called when a player dies.
     * @param event The event.
     */
    @Listener
    public void onPlayerDeath(DestructEntityEvent.Death event) {
        if ((event.getTargetEntity() instanceof Player)) {
            PlayerListener.onPlayerDeath(new SpongePlayerDeathEvent(event));
        }
    }

    /**
     * Called when a player logs in.
     * @param event The event.
     */
    @Listener
    public void onPlayerLogin(ClientConnectionEvent.Join event) {
        PlayerListener.onPlayerLogin(new SpongePlayerLoginEvent(event));
    }

    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @Listener
    public void onPlayerLogout(ClientConnectionEvent.Disconnect event) {
        PlayerListener.onPlayerLogout(new SpongePlayerLogoutEvent(event));
    }

    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @Listener
    public void onPlayerMessage(MessageEvent event, @All(ignoreEmpty=false) Player[] players) {
        if (players.length != 1) return;
        PlayerListener.onPlayerMessage(new SpongePlayerMessageEvent(event, players));
    }

    /**
     * Called when a player respawns.
     * @param event The event.
     */
    @Listener
    public void onPlayerRespawn(RespawnPlayerEvent event) {
        PlayerListener.onPlayerRespawn(new SpongePlayerRespawnEvent(event));
    }
}
