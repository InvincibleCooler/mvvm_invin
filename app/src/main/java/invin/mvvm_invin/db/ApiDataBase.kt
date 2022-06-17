package invin.mvvm_invin.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

private const val DB_VERSION = 1

@Database(entities = [ApiEntity::class], version = DB_VERSION, exportSchema = false)
abstract class ApiDataBase : RoomDatabase() {
    abstract fun apiDao(): ApiDao

    companion object {
        private const val TAG = "ApiDataBase"

        // For Singleton instantiation
        @Volatile
        private var instance: ApiDataBase? = null

        fun getInstance(context: Context): ApiDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ApiDataBase {
            return Room.databaseBuilder(context, ApiDataBase::class.java, "api_db")
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Log.d(TAG, "onCreate() - version : ${db.version}")
                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {
                            Log.d(TAG, "onOpen() - version : ${db.version}")
                        }
                    }
                )
                .build()
        }
    }
}

