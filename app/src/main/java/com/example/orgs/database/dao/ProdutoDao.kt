package com.example.orgs.database.dao

import androidx.room.*
import com.example.orgs.model.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto WHERE usuarioId = :usuarioId")
    fun buscaTodosDoUsuario(usuarioId: String) : Flow<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(produto: Produto)

    @Delete
    suspend fun remove(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscaPorId(id: Long) : Flow<Produto?>

    @Query("SELECT * FROM Produto ORDER BY nome ASC")
    fun ordenarPorNomeAsc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER BY nome DESC")
    fun ordenarPorNomeDesc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER BY descricao ASC")
    fun ordenarPorDescricaoAsc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER BY descricao DESC")
    fun ordenarPorDescricaoDesc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER BY valor ASC")
    fun ordenarPorValorAsc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER BY valor DESC")
    fun ordenarPorValorDesc(): Flow<List<Produto>>
}