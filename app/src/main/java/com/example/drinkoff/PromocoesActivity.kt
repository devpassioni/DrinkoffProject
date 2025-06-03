package com.example.drinkoff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import model.ProdutoDetalhado

class PromocoesActivity : AppCompatActivity() {

    private lateinit var recyclerPromocoes: RecyclerView
    private lateinit var adapter: PromocaoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promocoes)

        recyclerPromocoes = findViewById(R.id.recyclerPromocoes)
        recyclerPromocoes.layoutManager = LinearLayoutManager(this)

        val json = assets.open("produtos.json").bufferedReader().use { it.readText() }
        val listaProdutos = Gson().fromJson(json, Array<ProdutoDetalhado>::class.java).toList()

        adapter = PromocaoAdapter(listaProdutos)
        recyclerPromocoes.adapter = adapter
    }
}
