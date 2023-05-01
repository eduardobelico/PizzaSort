package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.TituloProdutosUsuarioBinding

class TituloProdutosUsuarioAdapter(
    context: Context,
    usuario: List<String?> = emptyList()
) : RecyclerView.Adapter<TituloProdutosUsuarioAdapter.TituloViewHolder>() {

    inner class TituloViewHolder(private val binding: TituloProdutosUsuarioBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(usuario: String?) {
            binding.tituloUsuario.text = usuario
        }
    }

    private val usuarioList: List<String?> = usuario.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TituloViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TituloProdutosUsuarioBinding.inflate(inflater, parent, false)
        return TituloViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TituloViewHolder, position: Int) {
        val usuario = if (usuarioList[position].isNullOrBlank()) {
            "Produtos Sem Usuário"
        } else usuarioList[position]
        holder.bindView(usuario)
    }

    override fun getItemCount(): Int = usuarioList.size

}