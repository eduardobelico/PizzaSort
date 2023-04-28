package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityFormularioCadastroUsuarioBinding
import com.example.orgs.extensions.toHash
import com.example.orgs.model.Usuario
import kotlinx.coroutines.launch

class FormularioCadastroUsuarioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioCadastroUsuarioBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
    }

    private fun configuraBotaoCadastrar() {
        binding.activityFormularioCadastroBotaoCadastrar.setOnClickListener {
            val novoUsuario = criaUsuario()
            lifecycleScope.launch {
                try {
                    dao.salvaUsuario(novoUsuario)
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(
                        this@FormularioCadastroUsuarioActivity,
                        "Falha ao cadastrar usuário",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun criaUsuario(): Usuario {
        val usuario = binding.activityFormularioCadastroUsuario.text.toString()
        val nome = binding.activityFormularioCadastroNome.text.toString()
        val senha = binding.activityFormularioCadastroSenha.text.toString().toHash()
        return Usuario(usuario, nome, senha)
    }
}