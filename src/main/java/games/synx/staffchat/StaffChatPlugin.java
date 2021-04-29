package games.synx.staffchat;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import games.synx.staffchat.util.trevor.IntercomListener;
import org.slf4j.Logger;

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

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {

        this.proxyServer.getEventManager().register(this, new IntercomListener());

    }
}
