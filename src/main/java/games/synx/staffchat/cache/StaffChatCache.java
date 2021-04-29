package games.synx.staffchat.cache;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.UUID;

public class StaffChatCache {

    private static final Set<UUID> TOGGLED_USERS = Sets.newHashSet();

    public static void addUser(UUID uuid) {
        TOGGLED_USERS.add(uuid);
    }

    public static void removeUser(UUID uuid) {
        TOGGLED_USERS.remove(uuid);
    }

    public static boolean contains(UUID uuid) {
        return TOGGLED_USERS.contains(uuid);
    }

}
