package dev.neuralnexus.taterlib.sponge.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntitySpawnEvent;
import dev.neuralnexus.taterlib.common.abstractions.utils.Location;
import dev.neuralnexus.taterlib.sponge.abstractions.entity.SpongeEntity;
import dev.neuralnexus.taterlib.sponge.abstractions.util.SpongeConversions;
import org.spongepowered.api.event.entity.SpawnEntityEvent;

/**
 * Sponge implementation of {@link AbstractEntitySpawnEvent}.
 */
public class SpongeEntitySpawnEvent implements AbstractEntitySpawnEvent {
    private final SpawnEntityEvent.Custom event;

    public SpongeEntitySpawnEvent(SpawnEntityEvent.Custom event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractEntity getEntity() {
        return new SpongeEntity(event.getEntities().get(0));
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getLocation() {
        return new Location(SpongeConversions.positionFromVector(event.getEntities().get(0).getLocation().getPosition()), getEntity().getDimension());
    }
}
