package com.example.orgs.database

import android.content.Context
import androidx.room.*
import com.example.orgs.database.converter.Converters
import com.example.orgs.database.dao.ProdutoDao
import com.example.orgs.database.dao.UsuarioDao
import com.example.orgs.model.Produto
import com.example.orgs.model.Usuario

@Database(
    entities = [Produto::class, Usuario::class], version = 3, autoMigrations = [
        AutoMigration (
            from = 1,
            to = 2
        ),
        AutoMigration (
            from = 2,
            to = 3
        )
    ], exportSchema = true
)

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
                "pizzasort-db"
            )
                .build()
                .also {
                    db = it
                }
        }
    }
}
