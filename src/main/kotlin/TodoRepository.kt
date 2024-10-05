package com.sergeypetrunin

import klite.jdbc.*
import klite.toValues
import javax.sql.DataSource

class TodoRepository(private val db: DataSource) {
    // private val todos = mutableListOf<Todo>()
    // fun list() = todos.toList()
    // fun save(todo: Todo) = todos.add(todo)
    // fun get(id: Id<Todo>) = todos.first { it.id == id }

    fun list() = db.select<Todo>("todos")
    fun save(todo: Todo) = db.upsert("todos", todo.toValues())
    fun get(id: Id<Todo>) = db.select<Todo>("todos", Todo::id to id).first()
}
