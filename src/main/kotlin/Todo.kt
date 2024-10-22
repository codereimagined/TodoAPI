package com.sergeypetrunin

import klite.*
import klite.jdbc.BaseEntity
import java.time.Instant

typealias Id<T> = TSID<T>
data class Todo(
    val item: String,
    val completedAt: Instant? = null,
    override val id: Id<Todo> = Id()
): BaseEntity<Id<Todo>>

fun Todo.persister1(): Map<String, Any?> {
    println("Help")
    return this.toValuesSkipping(Todo::completedAt) + mapOf("completed_at" to this.completedAt)
}
// TODO: Overwrite backward conversion, currently completedAt returns as null always
