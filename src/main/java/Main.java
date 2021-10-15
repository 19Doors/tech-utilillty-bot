import javax.security.auth.login.LoginException;

import fun.perTest;
import fun.race;
import general.botInvite;
import general.infoBot;
import general.ping;
import moderation.Ban;
import moderation.autoRole;
import moderation.autoRoleImpl;
import moderation.clear;
import moderation.createChannel;
import moderation.deleteChannel;
import moderation.kick;
import moderation.slowmode;
import moderation.unban;
import moderation.mute.mute;
import moderation.mute.unmute;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import prefix.prefix;

public class Main {
    public static void main(String[] args) throws LoginException{
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault("ODk4NDQyNzk2ODY2ODc5NTEw.YWkSDw.OhLrQ8OksYMZD_qUh0fGy3moV7E")
                                                .setMemberCachePolicy(MemberCachePolicy.ALL)
                                                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                                                .enableIntents(GatewayIntent.GUILD_MESSAGES)
                                                .enableIntents(GatewayIntent.GUILD_PRESENCES);

        //For Owner
        builder.addEventListeners(
                                //Owner
                                new prefix(),
                                
                                //Moderator
                                new clear(),
                                new createChannel(),
                                new deleteChannel(),
                                new slowmode(),
                                new autoRole(),
                                new autoRoleImpl(),
                                new kick(),
                                new mute(),
                                new unmute(),
                                new Ban(),
                                new unban(),
                                
                                //General
                                new ping(),
                                new botInvite(),
                                new infoBot(),
                                
                                //Fun
                                new perTest(),
                                new race());

        
        builder.build();
        
    }
}
