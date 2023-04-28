package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.orgs.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun salvaUsuario(usuario: Usuario)
}