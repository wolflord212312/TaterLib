package dev.neuralnexus.taterlib.common.abstractions.events;

/**
 * Abstract class for cancellable events.
 */
public interface AbstractCancellableEvent {
    /**
     * Gets whether the event is cancelled.
     * @return Whether the event is cancelled.
     */
    boolean isCancelled();

    /**
     * Sets whether the event is cancelled.
     * @param cancelled Whether the event is cancelled.
     */
    void setCancelled(boolean cancelled);
}
