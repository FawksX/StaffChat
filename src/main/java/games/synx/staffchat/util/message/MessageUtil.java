package games.synx.staffchat.util.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MessageUtil {

    /**
     * Parse a Message into MiniMessage
     * @param message Serialized Message
     * @return Component
     */
    public static Component parse(String message) {
        return MiniMessage.get().parse(message);
    }
}
