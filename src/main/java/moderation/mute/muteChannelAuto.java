package moderation.mute;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.ChannelManager;

public class muteChannelAuto extends ListenerAdapter {
    @Override
    public void onTextChannelCreate(TextChannelCreateEvent event){
        TextChannel tc = event.getChannel();
        ChannelManager cm = event.getChannel().getManager();
        Role role = event.getGuild().getRoleById("879698906387197952");
        tc.createPermissionOverride(role).setDeny(Permission.MESSAGE_WRITE).queue();
    }
}
