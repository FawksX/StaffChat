package games.synx.staffchat.util.command;

import com.velocitypowered.api.command.SimpleCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import java.util.List;

public abstract class AbstractCommand implements SimpleCommand {

    private final String permission;

    protected AbstractCommand(String permission) {
        this.permission = permission;
    }

    @Override
    public void execute(Invocation invocation) {

        if(!invocation.source().hasPermission(this.permission)) {

            Component component = Component.text("You do not have permission to use this command!")
                    .color(TextColor.color(255, 0, 0));

            invocation.source().sendMessage(component);
            return;
        }

        this.onCommand(invocation);

    }

    abstract void onCommand(Invocation invocation);

    /**
     * This has been marked as true so that the command does not get passed
     * through to the server.
     */
    @Override
    public boolean hasPermission(Invocation invocation) {
        return true;
    }
}
