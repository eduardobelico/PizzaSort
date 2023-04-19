package com.example.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.orgs.database.converter.Converters
import com.example.orgs.database.dao.ProdutoDao
import com.example.orgs.model.Produto

@Database(entities = [Produto::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao

    companion object {

         @Volatile
            private lateinit var db: AppDatabase

            fun instancia(context: Context): AppDatabase {
                if (::db.isInitialized) return db
                return Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "PizzaSort.db"
                ).allowMainThreadQueries()
                    .build().also {
                        db = it
                    }
            }
//        fun instancia(context: Context) : AppDatabase {
//            return Room.databaseBuilder(
//                context,
//                AppDatabase::class.java,
//                "PizzaSort.db"
//            ).allowMainThreadQueries()
//                .build()
//        }
    }
}
