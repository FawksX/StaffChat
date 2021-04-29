package games.synx.staffchat.util.trevor;

import com.velocitypowered.api.proxy.Player;
import games.synx.staffchat.StaffChatPlugin;
import games.synx.staffchat.config.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MessagePayload extends AbstractPayload {

    private final String username;
    private final String msg;
    private final String originServer;

    public MessagePayload(String username, String msg, String originServer) {
        super(RedisChannels.STAFFCHAT_CHANNEL);

        this.username = username;
        this.msg = msg;
        this.originServer = originServer;
    }

    @Override
    void execute() {

        String msgFormat = Config.getInstance().getConfig().getMessages().getMsgFormat();
        String formattedMsg = msgFormat.replace("%player%", this.username)
                .replace("%msg%", this.msg)
                .replace("%server%", this.originServer);

        Component component = MiniMessage.get().parse(formattedMsg);

        for(Player player : StaffChatPlugin.getInstance().getProxyServer().getAllPlayers()) {
            if(player.hasPermission("staffchat.view")) {
                player.sendMessage(component);
            }
        }
    }
}
