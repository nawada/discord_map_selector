package tokyo.gamestar

import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.api.events.EventSubscriber
import sx.blah.discord.handle.impl.events.MessageReceivedEvent
import sx.blah.discord.util.MessageBuilder

class AnnotationListener(val client: IDiscordClient) {

    @EventSubscriber
    fun onMessageReceiveEvent(event: MessageReceivedEvent): Unit {
        // when mentions @everyone or @here, end method
        val msg = event.message
        if (msg.mentionsEveryone() || msg.mentionsHere()) return

        if (event.client.applicationClientID == client.applicationClientID) {
            // answer to map name when mentions to bot
            MessageBuilder(client)
                    .withChannel(msg.channel)
                    .withContent(msg.author.mention(true) + " " + MapSelector().mapSelect())
                    .build()
        }
    }

}