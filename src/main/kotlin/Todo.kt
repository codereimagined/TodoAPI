package com.sergeypetrunin

import klite.*
import klite.jdbc.BaseEntity
import klite.jdbc.get
import klite.jdbc.getOptional
import java.sql.ResultSet
import java.time.Instant
import kotlin.reflect.KClass

typealias Id<T> = TSID<T>
data class Todo(
    val item: String,
    val completedAt: Instant? = null,
    override val id: Id<Todo> = Id()
): BaseEntity<Id<Todo>>

fun Todo.persister1(): Map<String, Any?> {
    return this.toValuesSkipping(Todo::completedAt) + mapOf("completed_at" to this.completedAt)
}

fun <T: Any> ResultSet.create1(type: KClass<T>): T {
    return type.create {
        val column = it.name!!
        if (column == "completedAt") getOptional<T>("completed_at", it.type).getOrDefault(AbsentValue)
        else if (it.isOptional) getOptional<T>(column, it.type).getOrDefault(AbsentValue)
        else get(column, it.type)
    }
}

