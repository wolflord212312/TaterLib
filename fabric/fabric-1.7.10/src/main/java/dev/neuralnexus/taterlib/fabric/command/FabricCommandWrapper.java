package dev.neuralnexus.taterlib.fabric.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;

import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandCallable;
import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandException;
import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandResult;
import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
import net.legacyfabric.fabric.api.util.Location;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/** Wraps a command callback into a Forge CommandBase. */
public class FabricCommandWrapper implements CommandCallable {
    private final Command command;

    public FabricCommandWrapper(Command command) {
        this.command = command;
    }

    @Override
    public CommandResult process(PermissibleCommandSource source, String arguments)
            throws CommandException {
        String[] args = arguments.split(" ");
        try {
            if (source instanceof PlayerEntity) {
                command.execute(new FabricPlayer((PlayerEntity) source), command.getName(), args);
            }
            command.execute(new FabricSender(source), command.getName(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommandResult.builder().successCount(1).build();
    }

    @Override
    public List<String> getSuggestions(
            PermissibleCommandSource source,
            String arguments,
            @Nullable Location<World> targetPosition)
            throws CommandException {
        return null;
    }

    @Override
    public boolean testPermission(PermissibleCommandSource source) {
        return command.getPermission().isEmpty() || source.hasPermission(command.getPermission());
    }

    @Override
    public Optional<Text> getShortDescription(PermissibleCommandSource source) {
        return Optional.empty();
    }

    @Override
    public Optional<Text> getHelp(PermissibleCommandSource source) {
        return Optional.empty();
    }

    @Override
    public Text getUsage(PermissibleCommandSource source) {
        return new TranslatableText(command.getUsage());
    }
}