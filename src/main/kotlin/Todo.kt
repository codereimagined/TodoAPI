package com.sergeypetrunin

import klite.TSID
import klite.jdbc.BaseEntity
import java.time.Instant

typealias Id<T> = TSID<T>
data class Todo(
    val item: String,
    val completed_at: Instant? = null, // TODO: Fix if possible
    override val id: Id<Todo> = Id()
): BaseEntity<Id<Todo>>
