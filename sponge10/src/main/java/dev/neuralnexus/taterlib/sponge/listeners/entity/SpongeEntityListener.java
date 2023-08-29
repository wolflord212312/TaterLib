package dev.neuralnexus.taterlib.sponge.listeners.entity;

import dev.neuralnexus.taterlib.common.listeners.enity.EntityListener;
import dev.neuralnexus.taterlib.sponge.abstractions.entity.SpongeEntity;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.entity.SpawnEntityEvent;

/**
 * Listens to entity events.
 */
public class SpongeEntityListener {
    /**
     * Called when an entity dies.
     * @param event The event.
     */
    @Listener
    public void onPlayerDeath(DestructEntityEvent.Death event) {
        EntityListener.onEntityDeath(new SpongeEntity(event.entity()), event.message().toString());
    }

    /**
     * Called when an entity is spawned.
     * @param event The event.
     */
    @Listener
    public void onEntitySpawn(SpawnEntityEvent.Pre event) {
        EntityListener.onEntitySpawn(new SpongeEntity(event.entities().get(0)));
    }
}
