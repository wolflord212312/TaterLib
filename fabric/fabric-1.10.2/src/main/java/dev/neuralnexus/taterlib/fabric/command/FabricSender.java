package dev.neuralnexus.taterlib.fabric.command;

import dev.neuralnexus.taterlib.command.Sender;

import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
import net.minecraft.text.TranslatableText;

import java.util.UUID;

/** The Fabric implementation of {@link Sender} */
public class FabricSender implements Sender {
    private final PermissibleCommandSource source;

    public FabricSender(PermissibleCommandSource source) {
        this.source = source;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public PermissibleCommandSource getSender() {
        return source;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        if (source.getEntity() == null) {
            return new UUID(0, 0);
        }
        return source.getEntity().getUuid();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return source.getName().asFormattedString();
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        source.sendMessage(new TranslatableText(message));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}