package com.sergeypetrunin

import klite.Config
import klite.json.JsonBody
import klite.Server
import klite.annotations.annotated
import klite.isDev
import klite.jdbc.*
import klite.json.JsonMapper

fun main() {
    println("Hello World!")

    // loads .env file if it exists, and uses values from file only if they are not set in the environment
    Config.useEnvFile()
    Server().apply {
        if (Config.isDev) startDevDB()
        use<DBModule>()
        use<DBMigrator>()
        use(JsonBody(JsonMapper(renderNulls = true)))
        context("/plain") {
             get("/hello") { "Hello, world!" }
        }
        context("/api") {
            useOnly<JsonBody>() // this removes other renderers in /api context
            annotated<TodoRoutes>("/todos")
        }
        start()
    }
}
