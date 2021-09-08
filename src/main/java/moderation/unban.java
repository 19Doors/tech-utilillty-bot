package moderation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import CommandManager.Comm;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tools.cmdhelp;

public class unban extends ListenerAdapter{
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String al[] = {"unbanuser"};
        Comm com = new Comm("unban", al, "It will **Unban** players for you.", event);
        List<Permission> ls = new ArrayList<Permission>();
        ls.add(Permission.BAN_MEMBERS);
        if(com.checkCondition(ls))
        {
            String id = "";
    
            String wrds[] = event.getMessage().getContentRaw().split(" ");
            if(event.getMessage().getMentionedUsers().isEmpty())
            {
                id = wrds[1];
            }
            else
            {
                try
                {
                    id = event.getMessage().getMentionedUsers().get(0).getId();
                }catch(Exception e)
                {
                    event.getMessage().reply(new EmbedBuilder().setTitle("Command Helper").setDescription("That User does'nt Exist").setColor(new Color(252,43,43)).setFooter("Tect Utilities").build()).queue();
                    return;
                }
            }

            try{
                Long.parseLong(id);
            }catch(Exception e){
                cmdhelp help = new cmdhelp();
                List<Field> fields = new ArrayList<Field>();
                fields.add(new Field("**Unban Command**", "This Command is used as: ```<prefix>unban <userIdorUserMention>```", false));
                help.wrongCommand(event, fields);

                return;
            }

            //Check if the user is in guild or not
            try{event.getGuild().getMemberById(id);}catch(Exception e){event.getMessage().reply(new EmbedBuilder().setTitle("Command Helper").setDescription("That User does'nt Exist").setColor(new Color(252,43,43)).setFooter("Tect Utilities").build()).queue();
            return;}

            //Check if user is real
            try
            {
                event.getGuild().getMemberById(id);
            }catch(Exception e)
            {
                event.getMessage().reply(new EmbedBuilder().setTitle("Command Helper").setDescription("That User is not in your server").setColor(new Color(252,43,43)).setFooter("Tect Utilities").build()).queue();
                return;
            }

            event.getChannel().sendMessage("Unbanned : "+event.getJDA().getUserById(id).getAsTag()).queue();
            event.getGuild().unban(event.getJDA().getUserById(id)).queue();
            
        }
    }
}
