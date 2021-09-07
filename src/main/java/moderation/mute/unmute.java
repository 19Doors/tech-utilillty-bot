package moderation.mute;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import CommandManager.Comm;

public class unmute extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
        {
            String al[] = {"unmute"};
            List<Permission> ls = new ArrayList<Permission>();
            ls.add(Permission.KICK_MEMBERS);
            Comm com = new Comm("unmute", al, "It will mute people", event);
            if(com.checkCondition(ls))
            {
                Message msg = event.getMessage();
                String content = msg.getContentRaw();
                if (event.getMessage().getMentionedUsers().isEmpty()) {
                    String a[] = content.split(" ");

                    //check even the member has the role or not
                    String id = a[1];
                    String roleId = event.getGuild().getRolesByName("Muted", true).get(0).getId();
                    Role role = event.getGuild().getRoleById(roleId);
                    event.getGuild().removeRoleFromMember(id, role).queue();
                    event.getChannel().sendMessage("Unmuted <@" + id + ">").queue();
                } else {
                    String a[] = content.split(" ");
                    String id = msg.getMentionedUsers().get(0).getId();
                    String roleId = event.getGuild().getRolesByName("Muted", true).get(0).getId();
                    Role role = event.getGuild().getRoleById(roleId);
                    event.getGuild().removeRoleFromMember(id, role).queue();
                    event.getChannel().sendMessage("Unmuted <@" + id + ">").queue();
                }
            }
        }
}
