package moderation;

import java.util.ArrayList;
import java.util.List;

import CommandManager.Comm;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ban extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String al[] = {"ban"};
        Comm com = new Comm("ban", al, "It will ban players for you.", event);
        List<Permission> ls = new ArrayList<Permission>();
        ls.add(Permission.BAN_MEMBERS);
        if(com.checkCondition(ls))
        {
            
        }
    }
}
