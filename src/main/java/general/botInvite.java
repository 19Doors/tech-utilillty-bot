package general;

import CommandManager.Comm;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class botInvite extends ListenerAdapter{

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String al[] = {"botinv", "help"};
        Comm com = new Comm("botInvite", al, "This will give you the invite for bot", event);
        if(com.checkConditionNoArg())
        {
            event.getChannel().sendMessage("Thanks for requesting, heres the bot invite: "+event.getJDA().getInviteUrl(Permission.ADMINISTRATOR)).queue();
        }
    }
    
}
