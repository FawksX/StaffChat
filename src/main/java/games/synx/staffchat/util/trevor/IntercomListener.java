package games.synx.staffchat.util.trevor;

import co.schemati.trevor.velocity.platform.event.VelocityNetworkMessageEvent;
import com.velocitypowered.api.event.Subscribe;

public class IntercomListener {

    @Subscribe
    public void onNetworkIntercomEvent(VelocityNetworkMessageEvent event) {
        if(event.payload() instanceof AbstractPayload) {
            ((AbstractPayload) event.payload()).execute();
        }
    }

}
