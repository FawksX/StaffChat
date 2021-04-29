package games.synx.staffchat;

import co.schemati.trevor.api.TrevorService;
import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import games.synx.staffchat.config.Config;
import games.synx.staffchat.util.trevor.IntercomListener;
import games.synx.staffchat.util.trevor.RedisChannels;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "staffchat",
        name = "StaffChat",
        version = "@version@",
        authors = {"FawksX"},
        dependencies = {
                @Dependency(id = "trevor")
        }
)
public class StaffChatPlugin {

    @Inject
    private Logger logger;

    @Inject
    private ProxyServer proxyServer;

    @Inject @DataDirectory
    private Path pluginDirectory;

    private static StaffChatPlugin instance;
    private static Config config;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        instance = this;

        if(!this.pluginDirectory.toFile().exists()) {
            this.pluginDirectory.toFile().mkdir();
        }

        config = new Config(this.pluginDirectory);

        this.proxyServer.getEventManager().register(this, new IntercomListener());

        TrevorService.getAPI().getDatabase().getIntercom().add(RedisChannels.STAFFCHAT_CHANNEL);

    }

    public static StaffChatPlugin getInstance() {
        return instance;
    }

    public static Config getConfig() {
        return config;
    }

    public ProxyServer getProxyServer() {
        return this.proxyServer;
    }
}
