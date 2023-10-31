package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerLogoutEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Bukkit implementation of {@link AbstractPlayerLogoutEvent}.
 */
public class BukkitPlayerLogoutEvent extends BukkitPlayerEvent implements AbstractPlayerLogoutEvent {
    private final PlayerQuitEvent event;

    public BukkitPlayerLogoutEvent(PlayerQuitEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLogoutMessage() {
        return event.getQuitMessage();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLogoutMessage(String message) {
        event.setQuitMessage(message);
    }
}
