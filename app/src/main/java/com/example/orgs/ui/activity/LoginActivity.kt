package com.example.orgs.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityLoginBinding
import com.example.orgs.extensions.toHash
import com.example.orgs.extensions.vaiPara
import com.example.orgs.preferences.dataStore
import com.example.orgs.preferences.usuarioLogadoPreferences
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val usuarioDao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
        configuraBotaoEntrar()
    }

    private fun configuraBotaoEntrar() {
        binding.activityLoginBotaoEntrar.setOnClickListener {
            val usuario = binding.activityLoginUsuario.text.toString()
            val senha = binding.activityLoginSenha.text.toString().toHash()
            lifecycleScope.launch {
                usuarioDao.autenticaUsuario(usuario, senha)?.let { usuario ->
                    dataStore.edit { preferences ->
                        preferences[usuarioLogadoPreferences] = usuario.id
                    }
                    vaiPara(ListaProdutosActivity::class.java)
                    finish()
                } ?: Toast.makeText(this@LoginActivity, "Falha na autenticação", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun configuraBotaoCadastrar() {
        binding.activityLoginBotaoCadastrar.setOnClickListener {
            vaiPara(FormularioCadastroUsuarioActivity::class.java)
        }
    }

}