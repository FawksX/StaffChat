package games.synx.staffchat.event.subscribe;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import games.synx.staffchat.cache.StaffChatCache;
import games.synx.staffchat.util.trevor.MessagePayload;

public class ChatPreprocessListener {

    @Subscribe
    public void onChatEvent(PlayerChatEvent event) {

        if(!event.getPlayer().hasPermission("staffchat.command.use")) {
            return;
        }

        if(StaffChatCache.contains(event.getPlayer().getUniqueId())) {
            event.setResult(PlayerChatEvent.ChatResult.denied());

            String server = event.getPlayer().getCurrentServer().get().getServerInfo().getName();

            new MessagePayload(event.getPlayer().getUsername(), event.getMessage(), server).post();
        }

    }

}
