package com.codinginflow.mvvmtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codinginflow.mvvmtodo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 2)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Wash the dishes","ii","12/12/8765",completed = true,important=true))
                dao.insert(Task("Do the laundry","ii","12/12/8765",completed=false,important=true))
                dao.insert(Task("Buy groceries","ii","12/12/8765", completed=false,important = true))
                dao.insert(Task("Prepare food","ii","8765" ,completed = true,important = true))
                dao.insert(Task("Call mom","ii","8765"))
                dao.insert(Task("Visit grandma","ii","8765", completed = true))
                dao.insert(Task("Repair my bike","ii","8765"))
                dao.insert(Task("Call Elon Musk","ii","8765"))
            }
        }
    }
}