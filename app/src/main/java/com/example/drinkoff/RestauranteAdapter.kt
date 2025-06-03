package com.example.drinkoff

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.Restaurante

class RestauranteAdapter(private val lista: List<Restaurante>) :
    RecyclerView.Adapter<RestauranteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView = view.findViewById(R.id.tvNome)
        val descricao: TextView = view.findViewById(R.id.tvDescricao)
        val info: TextView = view.findViewById(R.id.tvInfo)
        val imagem: ImageView = view.findViewById(R.id.imgRestaurante)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurante2, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurante = lista[position]
        holder.nome.text = restaurante.nome

        val categoriasTexto = restaurante.categorias.keys.joinToString(", ")
        holder.descricao.text = categoriasTexto

        holder.info.text = "Disponivel agora"

        val context = holder.itemView.context
        val imageName = restaurante.imagem.removeSuffix(".png")
        val resId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        if (resId != 0) {
            holder.imagem.setImageResource(resId)
        } else {
            holder.imagem.setImageResource(R.drawable.ic_banner_exemplo)
        }


        holder.itemView.setOnClickListener {
            val intent = Intent(context, PromocoesActivity::class.java)
            intent.putExtra("nome_restaurante", restaurante.nome)
            context.startActivity(intent)
        }
    }
}
