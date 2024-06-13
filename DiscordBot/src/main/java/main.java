import static net.dv8tion.jda.api.entities.Activity.watching;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class main {
    public static String prefix = "~";
    static JDA builder;
    public static void main(String[] args) throws LoginException, InterruptedException {
             builder = JDABuilder.createDefault(SecretToken.TOKEN).setChunkingFilter(ChunkingFilter.ALL).
                setMemberCachePolicy(MemberCachePolicy.ALL).enableIntents(GatewayIntent.GUILD_MEMBERS).addEventListeners(new Commands()).enableCache(CacheFlag.VOICE_STATE).setActivity(watching("Over You: Prefix: ~")).build().awaitReady();
         
         }
    
}
   
    /*  public static JDABuilder builder;
    public static String prefix = "~";
    public static void main(String[] args) throws LoginException {
        String token = "OTI5ODY2NDA0NzU5NDg2NDc0.Ydtjkg.Qj9AYitOSHccyGrbU7Zw_HeXmlo";
        builder = JDABuilder.createDefault(token);

        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);

        builder.setBulkDeleteSplittingEnabled(false);

        builder.setCompression(Compression.NONE);

        builder.setActivity(Activity.playing("Discord"));

        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        registerListeners();

        builder.build();

*/

    





