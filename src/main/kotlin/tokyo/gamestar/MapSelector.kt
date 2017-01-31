package tokyo.gamestar

import sx.blah.discord.api.ClientBuilder
import sx.blah.discord.api.IDiscordClient
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

fun main(args: Array<String>) {
    // args[0] == BotToken
    MapSelector().start(args[0])
}

class MapSelector() {
    private val mapNames = arrayListOf(
            "ECOPOINT: ANTARCTICA",
            "HANAMURA",
            "TEMPLE OF ANUBIS",
            "VOLSKAYA INDUSTRIES",
            "DORADO",
            "WATCHPOINT:GIBRALTAR",
            "ROUTE66",
            "KING'S ROW",
            "NUMBANI",
            "HOLLYWOOD",
            "NEPAL",
            "LIJIANG TOWER",
            "ILIOS",
            "EICHENWALDE",
            "OASIS"
    )

    fun start(botToken: String): Unit {
        val client = loginBot(botToken)
        dispatchEvent(client)
    }

    private fun loginBot(botToken: String): IDiscordClient {
        return BotBuilder.client(botToken)
    }

    private fun dispatchEvent(client: IDiscordClient): Unit {
        val d = client.dispatcher
        d.registerListener(AnnotationListener(client))
    }

    fun mapSelect(seed: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)): String {
        val value: Int = Math.abs(Random(seed).nextInt())
        return mapNames[value % mapNames.size]
    }
}

object BotBuilder {
    val client: (token: String) -> IDiscordClient = { token -> ClientBuilder().withToken(token).login() }
}