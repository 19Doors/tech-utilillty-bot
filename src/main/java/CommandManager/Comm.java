package CommandManager;

import java.util.List;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import prefix.prefix;
import userinfo.userPerms;

public class Comm{
    String name;
    String aliases[];
    String help;
    String wrds[];
    String prefixx;
    MessageReceivedEvent event;
    boolean helpNeed = false;
    userPerms up = new userPerms();

    
    
    public Comm(String name, String alias[], String help, MessageReceivedEvent event){
        this.name = name;
        aliases = alias;
        this.help = help;
        this.event = event;

        wrds = event.getMessage().getContentStripped().split(" ");

        //Get Prefix
        prefix pref = new prefix();
        try{
        prefixx =  pref.getPrefix(event.getGuild());
        }catch(IllegalStateException e){

        }
        
    }


    public boolean containsEmbed(MessageReceivedEvent event1){
        if(event1.getMessage().getEmbeds().isEmpty()){
            return false;
        }else{
            return true;
        }
        
    }

    public boolean checkCondition(){
        
        if(containsEmbed(event)){
            return false;
        }else{
        
        String content = event.getMessage().getContentStripped();
        //Check for prefix
        if(Character.toString(content.charAt(0)).compareTo(prefixx)==0){
            if(wrds[0].substring(1).equalsIgnoreCase(name)){
                if(wrds.length==1){
                    event.getChannel().sendMessage(help).queue();
                    return false;
                }else{
                    return true;
                }
            }else{
                for(int i = 0; i<aliases.length; i++){
                    if(aliases[i].equalsIgnoreCase(wrds[0].substring(1))){
                        if(wrds.length==1){
                            event.getChannel().sendMessage(help).queue();
                            return false;
                        }else{
                            return true;
                        }
                    }
                }
            }
        }else{
            return false;
        }

        
    
    return false;
        }
    }
    

    public boolean checkCondition(List<Permission> perms){
        if(containsEmbed(event))
        {
            return false;
        }else{
        String content = event.getMessage().getContentDisplay();

        //Check for prefix
        if(Character.toString(content.charAt(0)).compareTo(prefixx)==0){
            
            if(wrds[0].substring(1).equalsIgnoreCase(name)){
                if(up.userHasPerms(perms, event.getAuthor().getId(), event)){

                if(wrds.length==1){
                    event.getChannel().sendMessage(help).queue();
                    return false;
                }else{
                    return true;
                }
            }else{
                event.getChannel().sendMessage("You don't have permission to do that!").queue();
                return false;
            }
            }else{
                for(int i = 0; i<aliases.length; i++){
                    if(aliases[i].equalsIgnoreCase(wrds[0].substring(1))){
                        if(up.userHasPerms(perms, event.getAuthor().getId(), event)){
                        if(wrds.length==1){
                            event.getChannel().sendMessage(help).queue();
                            return false;
                        }else{
                            return true;
                        }
                    }else{
                        event.getChannel().sendMessage("Your role is too low for that!").queue();
                        return false;
                    }
                    }
                }
            }
        }else{
            return false;
        }

        return false;
    }
    }

    public boolean checkConditionNoArg(){
        if(containsEmbed(event))
        {
        return false;
        }

        Message msg = event.getMessage();
        String content = msg.getContentStripped();
        

        //Check for prefix
        if(Character.toString(wrds[0].charAt(0)).equals(prefixx)){
            if(wrds[0].substring(1).equalsIgnoreCase(name)){
                if(wrds.length==1){
                    return true;
                }else{
                    event.getChannel().sendMessage(help).queue();
                    return false;
                }
            }else{
                for(int i = 0; i<aliases.length; i++){
                    if(aliases[i].equalsIgnoreCase(wrds[0].substring(1))){
                        if(wrds.length==1){
                            return true;
                        }else{
                            event.getChannel().sendMessage(help).queue();
                            return false;
                        }
                    }
                }
            }
        }else{
            return false;
        }

        return false;
    }

    public boolean checkConditionNoArg(List<Permission> perms){

        if(containsEmbed(event))
        {
        return false;
        }

        Message msg = event.getMessage();
        String content = msg.getContentStripped();
        //Check for prefix
        if(Character.toString(wrds[0].charAt(0)).equals(prefixx)){
            if(wrds[0].substring(1).equalsIgnoreCase(name)){
                if(up.userHasPerms(perms, event.getAuthor().getId(), event)){

                    if(wrds.length==1){
                        return true;
                    }else{
                        event.getChannel().sendMessage(help).queue();
                        return false;
                    }
            }else{
                event.getChannel().sendMessage("You don't have permission to do that!").queue();
                return false;
            }
            }else{
                for(int i = 0; i<aliases.length; i++){
                    if(aliases[i].equalsIgnoreCase(wrds[0].substring(1))){
                        if(up.userHasPerms(perms, event.getAuthor().getId(), event)){
                            if(wrds.length==1){
                                return true;
                            }else{
                                event.getChannel().sendMessage(help).queue();
                                return false;
                            }
                    }else{
                        event.getChannel().sendMessage("Your role is too low for that!").queue();
                        return false;
                    }
                    }
                }
            }
        }else{
            return false;
        }

        return false;
    }




}