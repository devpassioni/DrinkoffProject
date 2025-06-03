package com.example.drinkoff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import model.Restaurante

class ResultadoActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RestauranteAdapter
    private lateinit var listaCompleta: List<Restaurante>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordem_produto)

        val termo = intent.getStringExtra("termo_busca")?.lowercase() ?: ""

        recyclerView = findViewById(R.id.recyclerViewProdutos)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val jsonString = assets.open("restaurantes.json").bufferedReader().use { it.readText() }
        listaCompleta = Gson().fromJson(jsonString, Array<Restaurante>::class.java).toList()

        val resultados = listaCompleta.filter { restaurante ->
            restaurante.categorias.any { (categoria, itens) ->
                categoria.lowercase().contains(termo) || itens.any { it.lowercase().contains(termo) }
            }
        }

        adapter = RestauranteAdapter(resultados)
        recyclerView.adapter = adapter
    }
}
