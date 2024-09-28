package com.sergeypetrunin

import klite.Config
import klite.json.JsonBody
import klite.Server
import klite.TSID
import klite.annotations.annotated
import klite.json.JsonMapper
import java.time.Instant

fun main() {
    println("Hello World!")

    // loads .env file if it exists, and uses values from file only if they are not set in the environment
    Config.useEnvFile()
    Server().apply {
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

typealias Id<T> = TSID<T>
data class Todo(val item: String, val completedAt: Instant? = null, val id: Id<Todo> = Id())
