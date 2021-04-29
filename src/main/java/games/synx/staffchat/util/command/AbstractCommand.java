package games.synx.staffchat.util.command;

import com.velocitypowered.api.command.SimpleCommand;
import games.synx.staffchat.config.Config;

public abstract class AbstractCommand implements SimpleCommand {

    private final String permission;

    protected AbstractCommand(String permission) {
        this.permission = permission;
    }

    @Override
    public void execute(Invocation invocation) {

        if(!invocation.source().hasPermission(this.permission)) {

            invocation.source().sendMessage(Config.getInstance().getConfig().getMessages().getCmdNoPermissionMsg());
            return;
        }

        this.onCommand(invocation);

    }

    protected abstract void onCommand(Invocation invocation);

    /**
     * This has been marked as true so that the command does not get passed
     * through to the server.
     */
    @Override
    public boolean hasPermission(Invocation invocation) {
        return true;
    }
}
