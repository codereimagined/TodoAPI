package com.sergeypetrunin

import klite.jdbc.*
import java.sql.ResultSet
import javax.sql.DataSource


class TodoRepository(db: DataSource): BaseCrudRepository<Todo, Id<Todo>>(db, "todos") {
    override val orderAsc get() = "order by $table.completed_at"
    override fun ResultSet.mapper(): Todo = this.create1(Todo::class)
    override fun Todo.persister(): Map<String, Any?> = this.persister1()

    // private val todos = mutableListOf<Todo>()
    // fun list() = todos.toList()
    // fun save(todo: Todo) = todos.add(todo)
    // fun get(id: Id<Todo>) = todos.first { it.id == id }

    // fun list() = db.select<Todo>("todos")
    // fun save1(todo: Todo) = db.upsert("todos", todo.persister())
    // fun get(id: Id<Todo>) = db.select<Todo>("todos", Todo::id to id).first()

    fun delete(id: Id<Todo>) = db.delete("todos", Todo::id to id);
}
