package games.synx.staffchat.util.trevor;

import co.schemati.trevor.api.TrevorService;
import co.schemati.trevor.api.network.event.EventProcessor;
import co.schemati.trevor.api.network.event.NetworkEvent;
import co.schemati.trevor.api.network.payload.NetworkPayload;

public abstract class AbstractPayload extends NetworkPayload<String> {

    private final String channel;

    protected AbstractPayload(String channel) {
        this.channel = channel;
    }

    @Override
    public EventProcessor.EventAction<? extends NetworkEvent> process(EventProcessor eventProcessor) {
        return eventProcessor.onMessage(this);
    }

    abstract void execute();

    public void post() {
        TrevorService.getAPI().getDatabaseProxy().post(channel, this);
    }

}
