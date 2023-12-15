package dev.neuralnexus.taterlib.fabric.hooks.permissions;

import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.fabric.command.FabricSender;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

import me.lucko.fabric.api.permissions.v0.Permissions;

/** A hook for Fabric permissions */
public class FabricPermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "fabricpermissions";
    }

    /**
     * Get if a sender has a permission
     *
     * @param sender The sender to check
     * @param permission The permission to check
     * @return If the sender has the permission
     */
    @Override
    public boolean hasPermission(Sender sender, String permission) {
        if (sender instanceof Player) {
            return Permissions.check(((FabricPlayer) sender).getPlayer(), permission, 4);
        } else {
            return Permissions.check(((FabricSender) sender).getSender(), permission, 4);
        }
    }
}