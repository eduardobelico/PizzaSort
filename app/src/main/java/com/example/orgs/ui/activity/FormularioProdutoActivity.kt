package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto
import com.example.orgs.model.Usuario
import com.example.orgs.ui.dialog.FormularioImagemDialog
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.math.BigDecimal

class FormularioProdutoActivity : UsuarioBaseActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
    }

    private var url: String? = null
    private var produtoId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoSalvar()
        setContentView(binding.root)
        title = "Cadastrar Pizza"
        binding.activityFormularioProdutoImagem.setOnClickListener() {
            FormularioImagemDialog(this)
                .showDialog(url) { imagem ->
                    url = imagem
                    binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)
                }
        }
        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        tentaBuscarProduto()
    }

    private fun tentaBuscarProduto() {
        lifecycleScope.launch {
            produtoDao.buscaPorId(produtoId).collect { produto ->
                produto?.let {
                    title = "Alterar Produto"
                    binding.activityFormularioUsuario.visibility =
                        if (produto.salvoSemUsuario()) {
                            setCampoUsuario()
                            VISIBLE
                        } else GONE
                    preencheCampos(it)
                }
            }
        }
    }

    private fun setCampoUsuario() {
        lifecycleScope.launch {
            usuarios().map { usuarios ->
                usuarios.map { it.id }
            }.collect() { usuarios ->
                setAutoCompleteTextView(usuarios)
            }
        }
    }

    private fun setAutoCompleteTextView(usuarios: List<String>) {
        val campoUsuario = binding.activityFormularioUsuario
        val adapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            usuarios
        )
        campoUsuario.setAdapter(adapter)
        campoUsuario.setOnFocusChangeListener { _, focado ->
            if (!focado) {
                usuarioExistente(usuarios)
            }
        }
    }

    private fun usuarioExistente(usuarios: List<String>): Boolean {
        val campoUsuario = binding.activityFormularioUsuario
        val usuarioId = campoUsuario.text.toString()
        if (!usuarios.contains(usuarioId)) {
            campoUsuario.error = "usuário inexistente!"
            return false
        }
        return true
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    private fun preencheCampos(produto: Produto) {
        url = produto.imagem
        with(binding) {
            activityFormularioProdutoImagem.tentaCarregarImagem(produto.imagem)
            activityFormularioProdutoNome.setText(produto.nome)
            activityFormularioProdutoDescricao.setText(produto.descricao)
            activityFormularioProdutoValor.setText(produto.valor.toPlainString())
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar

        botaoSalvar.setOnClickListener {
            lifecycleScope.launch {
                tentaSalvarProduto()
            }
        }
    }

    private suspend fun tentaSalvarProduto() {
        usuario.value?.let { usuario ->
            try {
                val usuarioId = defineUsuario(usuario)
                val produtoNovo = criaProduto(usuarioId)
                produtoDao.salva(produtoNovo)
                finish()
            } catch (e: RuntimeException) {
                Log.e("FormularioProduto", "configuraBotaoSalvar:", e)
            }
        }
    }

    private suspend fun defineUsuario(usuario: Usuario): String = produtoDao
        .buscaPorId(produtoId)
        .first()?.let { produtoEncontrado ->
            if (produtoEncontrado.usuarioId.isNullOrBlank()) {
                val usuarios = usuarios()
                    .map {
                        usuariosEncontrados ->
                        usuariosEncontrados.map { it.id }
                    }.first()
                if (usuarioExistente(usuarios)) {
                    val campoUsuario = binding.activityFormularioUsuario
                    return campoUsuario.text.toString()
                } else {
                    throw RuntimeException("Tentou salvar produto com usuário Inexistente")
                }
            }
            null
        } ?: usuario.id

    private fun criaProduto(usuarioId: String): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()

        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            id = produtoId,
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url,
            usuarioId = usuarioId
        )
    }
}