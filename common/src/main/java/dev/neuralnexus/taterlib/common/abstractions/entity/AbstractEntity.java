package dev.neuralnexus.taterlib.common.abstractions.entity;

import java.util.UUID;

/**
 * The interface for an AbstractEntity
 */
public interface AbstractEntity {
    /**
     * Get the UUID of the entity
     * @return The UUID of the entity
     */
    UUID getUniqueId();

    /**
     * Get the id of the entity
     * @return The id of the entity
     */
    int getEntityId();

    /**
     * Remove the entity
     */
    void remove();

    /**
     * Get the type of the entity
     * @return The type of the entity
     */
    String getType();

    /**
     * Get the custom name of the entity
     * @return The custom name of the entity
     */
    String getCustomName();

    /**
     * Set the custom name of the entity
     * @param name The custom name of the entity
     */
    void setCustomName(String name);
}