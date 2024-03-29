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

public class Ban extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String al[] = {"banuser"};
        Comm com = new Comm("ban", al, "It will **ban** players for you.", event);
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

            int delday = 0;
            try{
                delday = Integer.parseInt(wrds[2]);
                Long.parseLong(id);
            }catch(Exception e){
                cmdhelp help = new cmdhelp();
                List<Field> fields = new ArrayList<Field>();
                fields.add(new Field("**Ban Command**", "This Command is used as: ```<prefix>ban <userIdorUserMention> <delMSGDays> <reasonOptional>```", false));
                help.wrongCommand(event, fields);

                return;
            }

            //Check if the user is in guild or not
            try{event.getGuild().getMemberById(id);}catch(Exception e){event.getChannel().sendMessage(new EmbedBuilder().setTitle("Command Helper").setDescription("That User does'nt Exist").setColor(new Color(252,43,43)).setFooter("Tect Utilities").build()).queue();
            return;}

            String reason = wrds[3];

            if(reason.isEmpty())
            {
                reason = "No reason";
            }

            //Check if user is real
            try
            {
                if(event.getGuild().getMemberById(id).equals(null))
                {
                    event.getChannel().sendMessage("User doesnt exist").queue();
                }
            }catch(Exception e)
            {
                event.getChannel().sendMessage(new EmbedBuilder().setTitle("Command Helper").setDescription("That User is not in your server").setColor(new Color(252,43,43)).setFooter("Tect Utilities").build()).queue();
                return;
            }
            
            final String reas = reason;
            event.getJDA().getUserById(id).openPrivateChannel().queue(ok1 -> {ok1.sendMessage("You were banned from "+event.getGuild().getName()+" for "+reas).queue();});
            event.getChannel().sendMessage("Banned : "+event.getJDA().getUserById(id).getAsTag()).queue();
            event.getGuild().ban(event.getJDA().getUserById(id),delday, reason).queue();
            
        }
    }
}
