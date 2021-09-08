package moderation.mute;

import userinfo.Roles.createRole;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.managers.ChannelManagerImpl;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import userinfo.getIdMention;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import CommandManager.Comm;


public class mute extends ListenerAdapter {
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String al[] = {"mute"};
        List<Permission> ls = new ArrayList<Permission>();
        ls.add(Permission.KICK_MEMBERS);
        Comm com = new Comm("mute", al, "It will mute people", event);
        if(com.checkCondition(ls))
        {
            //Basic stuff
            Message msg = event.getMessage();
            String content = msg.getContentRaw();
            String words[] = content.split(" ");
            String userid = "";

            //Get id of user
            if (msg.getMentionedUsers().isEmpty()) {
                //Getting Id of user
                userid = words[1];
            } else {
                //Getting User
                User user = msg.getMentionedUsers().get(0);
                //Getting Id of user
                getIdMention getIdMent = new getIdMention();
                userid = getIdMent.idFromMention(event, user);
            }

            User userMain = event.getGuild().getJDA().retrieveUserById(userid).complete();
            //Check if person muted
            muteOrNot isMute = new muteOrNot();
            Member usermember = event.getGuild().getMemberById(userid);
            boolean ismute = isMute.checkMute(event, usermember);

            if (ismute) {
                event.getChannel().sendMessage("Person " + userMain.getAsMention() + " is already muted!!").queue();
                return;
            } else {
                //Code below is for unmuted people

                //Checking if server has muted role or not
                if (checkMutedRole(event)) {
                    //If server has muted role
                    //Do nothing
                } else {
                    //If server do have muted role then
                    //Create Role
                    createRole cr = new createRole();
                    cr.createMutedRole(event, "Muted");
                }

                //Now process of muting

                //Getting role info
                Role role = event.getGuild().getRolesByName("Muted", true).get(0);
                String roleid = role.getId();

                //Giving member Muted role
                event.getGuild().addRoleToMember(userid, role).queue();
                event.getChannel().sendMessage("Muted " + userMain.getAsMention()).queue();

                //Timer for unmuting
                final String useridfinal = userid;

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        event.getGuild().removeRoleFromMember(useridfinal, role).queue();
                        event.getChannel().sendMessage("Unmuted " + userMain.getAsMention()).queue();
                    }
                };

                //Getting time
                
                String timestring = "";
                for (int i = 0; i < words[2].length() - 1; i++) {
                    timestring = timestring + (Character.toString(words[2].charAt(i)));
                }
                int time = Integer.parseInt(timestring);
                


                //Checking for last word
                int word2len = words[2].length();
                if (words[2].charAt((word2len - 1)) == 's') {
                    timer.schedule(task, (1000) * time);
                }
                if (words[2].charAt((word2len - 1)) == 'm') {
                    timer.schedule(task, (1000 * 60) * time);
                }
                if (words[2].charAt((word2len - 1)) == 'h') {
                    timer.schedule(task, (1000 * 3600) * time);
                }
                if (words[2].charAt((word2len - 1)) == 'd') {
                    timer.schedule(task, (1000 * 86400) * time);
                }
                if (words[2].charAt((word2len - 1)) == 'o') {
                    timer.schedule(task, (1000 * 2592000) * time);
                }

            }
        
        }
    }

    public boolean checkMutedRole(MessageReceivedEvent event){
        int se = event.getGuild().getRolesByName("Muted", true).size();
        boolean contains = true;
        if(se==0)
            contains= false;

        if(contains)
            return true;
        else
            return false;
    }

}