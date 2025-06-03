package com.example.drinkoff

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import model.ProdutoDetalhado

class PromocaoAdapter(private val lista: List<ProdutoDetalhado>) :
    RecyclerView.Adapter<PromocaoAdapter.PromocaoViewHolder>() {

    class PromocaoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagem: ImageView = view.findViewById(R.id.imgProduto)
        val nome: TextView = view.findViewById(R.id.tvNomeProduto)
        val preco: TextView = view.findViewById(R.id.tvPreco)
        val voucher: TextView = view.findViewById(R.id.tvVoucher)
        val restaurante: TextView = view.findViewById(R.id.tvRestaurante)
        val btnGerar: Button = view.findViewById(R.id.btnGerarVoucher)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromocaoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_promocao, parent, false)
        return PromocaoViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: PromocaoViewHolder, position: Int) {
        val produto = lista[position]
        holder.nome.text = produto.nome
        holder.preco.text = "R$ %.2f".format(produto.preco)
        holder.restaurante.text = "Restaurante: ${produto.restaurante}"
        holder.voucher.text = ""

        val context = holder.itemView.context
        val imageName = produto.imagem.removeSuffix(".png")
        val resId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        if (resId != 0) {
            holder.imagem.setImageResource(resId)
        } else {
            holder.imagem.setImageResource(R.drawable.ic_banner_exemplo)
        }

        holder.btnGerar.setOnClickListener {
            val codigo = "DRINK-${(1000..9999).random()}"


            val intent = Intent(context, VoucherGeradoActivity::class.java)
            intent.putExtra("voucher", codigo)
            context.startActivity(intent)
        }
    }
}
