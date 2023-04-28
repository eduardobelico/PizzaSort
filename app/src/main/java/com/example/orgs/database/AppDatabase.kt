package com.example.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.orgs.database.converter.Converters
import com.example.orgs.database.dao.ProdutoDao
import com.example.orgs.database.dao.UsuarioDao
import com.example.orgs.model.Produto
import com.example.orgs.model.Usuario

@Database(entities = [Produto::class, Usuario::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun produtoDao(): ProdutoDao

    abstract fun usuarioDao(): UsuarioDao

    companion object {

        @Volatile
        private lateinit var db: AppDatabase

        fun instancia(context: Context): AppDatabase {
            if (::db.isInitialized) return db
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "PizzaSort.db"
            )
                .build()
                .also {
                    db = it
                }
        }
    }
}
