package games.synx.staffchat.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import games.synx.staffchat.cache.StaffChatCache;
import games.synx.staffchat.config.Config;
import games.synx.staffchat.util.command.AbstractCommand;
import games.synx.staffchat.util.trevor.MessagePayload;

import java.util.List;
import java.util.Optional;

public class StaffChatCommand extends AbstractCommand {

    protected StaffChatCommand() {
        super("staffchat.command.use");
    }

    @Override
    protected void onCommand(Invocation invocation) {

        if(!(invocation.source() instanceof Player)) {
            return;
        }

        Player player = (Player) invocation.source();
        String[] args = invocation.arguments();
        Config config = Config.getInstance();

        if(args.length == 0) {

            if(StaffChatCache.contains(player.getUniqueId())) {
                StaffChatCache.removeUser(player.getUniqueId());
                player.sendMessage(config.getConfig().getMessages().getCmdStaffChatUntoggledMsg());
            } else {
                StaffChatCache.addUser(player.getUniqueId());
                player.sendMessage(config.getConfig().getMessages().getCmdStaffChatToggledMsg());
            }
            return;
        }

        Optional<ServerConnection> serverConnection = player.getCurrentServer();

        if(serverConnection.isEmpty()) {
            return;
        }

        RegisteredServer server = serverConnection.get().getServer();
        String message = String.join(" ", args);

        new MessagePayload(player.getUsername(), message, server.getServerInfo().getName()).post();
    }
}
