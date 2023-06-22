package dev.neuralnexus.taterapi.common.listeners.player;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import dev.neuralnexus.taterapi.common.relay.MessageRelay;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for player logins and sends them to the message relay.
 */
public interface PlayerLoginListener {
    /**
     * Called when a player logs in, and sends it to the message relay.
     * @param taterPlayer The player.
     */
    default void taterPlayerLogin(TaterPlayer taterPlayer) {
        runTaskAsync(() -> {
            try {
                MessageRelay relay = MessageRelay.getInstance();

                // Add the TaterPlayer to the cache
                relay.setTaterPlayerInCache(taterPlayer.getUUID(), taterPlayer);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}