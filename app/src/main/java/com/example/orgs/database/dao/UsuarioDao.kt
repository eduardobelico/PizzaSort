package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.orgs.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Insert
    suspend fun salvaUsuario(usuario: Usuario)

    @Query("SELECT * FROM Usuario WHERE id = :usuarioId AND senha = :senha")
    suspend fun autenticaUsuario(
        usuarioId: String,
        senha: String
    ): Usuario?

    @Query("SELECT * FROM Usuario WHERE id = :usuarioId")
    fun buscaUsuarioPorId(usuarioId: String): Flow<Usuario>

    @Query("SELECT * FROM Usuario")
    fun buscaTodosUsuarios(): Flow<List<Usuario>>
}