import javax.security.auth.login.LoginException;

import fun.perTest;
import fun.race;
import general.botInvite;
import general.infoBot;
import general.ping;
import moderation.autoRole;
import moderation.autoRoleImpl;
import moderation.clear;
import moderation.createChannel;
import moderation.deleteChannel;
import moderation.slowmode;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import prefix.prefix;

public class Main {
    public static void main(String[] args) throws LoginException{
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault("ODc1MDE5NTk3ODE5MDMxNTgy.YRPbgQ.lC86f8j4dgXirhxQShGJmLgkfR4")
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
