package com.sergeypetrunin

class TodoRepository {
    private val todos = mutableListOf<Todo>()

    fun list() = todos.toList()
    fun save(todo: Todo) = todos.add(todo)
}
