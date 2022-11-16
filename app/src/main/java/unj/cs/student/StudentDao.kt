package unj.cs.student

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)
    @Update
    suspend fun update(student: Student)
    @Delete
    suspend fun delete(student: Student)
    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Int): Flow<Student>
    @Query("SELECT * FROM student WHERE ids = :ids")
    fun getStudentByIds(ids: String): Flow<Student>
    @Query("SELECT * FROM student")
    fun getStudentList(): Flow<List<Student>>
}