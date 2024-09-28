package com.sergeypetrunin

import klite.annotations.*

class TodoRoutes(private val repo: TodoRepository) {
    @GET fun todos() = repo.list()
    @POST fun save(todo: Todo) = repo.save(todo)
    @GET("/:id") fun todoById(@PathParam id: Id<Todo>) = repo.get(id)
}
