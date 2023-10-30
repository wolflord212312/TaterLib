package dev.neuralnexus.taterlib.neoforge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLogoutEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * Forge implementation of {@link AbstractPlayerLogoutEvent}.
 */
public class NeoForgePlayerLogoutEvent extends NeoForgePlayerEvent implements AbstractPlayerLogoutEvent {
    private final PlayerEvent.PlayerLoggedOutEvent event;
    private String logoutMessage = "";

    public NeoForgePlayerLogoutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLogoutMessage() {
        if (!this.logoutMessage.isEmpty()) {
            return this.logoutMessage;
        }
        return event.getEntity().getName().getString() + " left the game";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
