import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import com.sergeypetrunin.Id
import org.junit.jupiter.api.Test
import com.sergeypetrunin.Todo
import com.sergeypetrunin.create1
import com.sergeypetrunin.persister1
import io.mockk.every
import io.mockk.mockk
import java.sql.ResultSet
import java.time.Instant


class TodoTest {
    @Test fun persist1() {
        val todo = Todo("test", id = Id(1L))

        expect(todo.persister1()).toContain("id" to todo.id, "item" to "test", "completed_at" to null)
    }

    @Test fun `should create Todo from ResultSet`() {
        val resultSet = mockk<ResultSet>()
        every { resultSet.getObject("item") } returns "Test Task"
        every { resultSet.getObject("completed_at") } returns java.sql.Timestamp.from(Instant.ofEpochSecond(1000))

        val todo = resultSet.create1(Todo::class)

        expect(todo.item).toEqual("Test Task")
        expect(todo.completedAt).toEqual(Instant.ofEpochSecond(1000))
    }
}
