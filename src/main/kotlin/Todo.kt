package com.sergeypetrunin

import klite.TSID
import java.time.Instant

typealias Id<T> = TSID<T>
data class Todo(val item: String, val completedAt: Instant? = null, val id: Id<Todo> = Id())
