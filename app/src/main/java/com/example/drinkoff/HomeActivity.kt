package com.example.drinkoff

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity



class HomeActivity : AppCompatActivity() {

    private lateinit var searchBar: EditText
    private lateinit var categoriaViews: List<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.title = "Página Inicial"


        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        searchBar = findViewById(R.id.searchBar)
        searchBar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val termo = searchBar.text.toString().trim()
                redirecionarParaResultado(termo)
                true
            } else {
                false
            }
        }


        categoriaViews = listOf(
            findViewById(R.id.cardVinho),
            findViewById(R.id.cardCerveja),
            findViewById(R.id.cardDrinks),
            findViewById(R.id.cardCasa),
            findViewById(R.id.cardSemAlcool),
            findViewById(R.id.cardDestilado)
        )

        val categorias = listOf("Vinho", "Cerveja", "Drinks", "Beba em Casa", "Sem Álcool", "Destilado")

        categoriaViews.forEachIndexed { index, view ->
            view.setOnClickListener {
                redirecionarParaResultado(categorias[index])
            }
        }
    }

    private fun redirecionarParaResultado(termo: String) {
        val intent = Intent(this, ResultadoActivity::class.java)
        intent.putExtra("termo_busca", termo)
        startActivity(intent)
    }
}
