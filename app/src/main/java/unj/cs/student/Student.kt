package unj.cs.student

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "ids")
    val ids: String,
    @ColumnInfo(name = "name")
    val name: String
    // var ids : String, var name: String
)
