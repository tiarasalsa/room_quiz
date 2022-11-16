package unj.cs.student

import android.app.Application

class StudentApplication : Application() {
    val database: StudentDatabase by lazy { StudentDatabase.getDatabase(this) }
}