package userinfo.Roles;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.RoleAction;

import java.awt.*;
import java.util.List;

public class createRole{

    public String createMutedRole(MessageReceivedEvent event, String name){
        RoleAction mute = event.getGuild().createRole();
        mute.setName(name);
        mute.setPermissions(Permission.VIEW_CHANNEL);
        mute.setColor(23711147);

        Role mute12 = mute.complete();

        List<TextChannel> tcl = event.getGuild().getTextChannels();
        int size = tcl.size();
        for(int i = 0; i<size; i++){
            TextChannel test = tcl.get(i);
            test.createPermissionOverride(mute12).setDeny(Permission.MESSAGE_WRITE).queue();
        }

        return mute.getGuild().getId();
    }

    public static void main(String[] args) {

    }
}