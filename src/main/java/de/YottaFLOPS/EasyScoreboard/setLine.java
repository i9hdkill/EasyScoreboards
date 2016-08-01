package de.YottaFLOPS.EasyScoreboard;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

class setLine implements CommandExecutor {

    private final Main plugin;

    setLine(Main instance) {
        plugin = instance;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        if(args.<Integer>getOne("Line").isPresent()) {
            int line = args.<Integer>getOne("Line").get();
            if (args.<String>getOne("New Text").isPresent()) {
                String newText = args.<String>getOne("New Text").get();
                Player player = (Player) Sponge.getServer().getOnlinePlayers().toArray()[0];

                plugin.setLine(newText, line, player, src);

            } else {
                src.sendMessage(Text.of(TextColors.RED, "Missing text"));
            }
        } else {
            src.sendMessage(Text.of(TextColors.RED, "Missing line"));
        }

        plugin.stopTPS();
        if(plugin.checkIfUsedTPS()) {
            plugin.startTPS();
        }

        return CommandResult.success();
    }
}
