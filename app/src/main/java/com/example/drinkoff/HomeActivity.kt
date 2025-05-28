package com.example.drinkoff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) // Crie esse layout com um Button para logout

        // Define título da ActionBar
        supportActionBar?.title = "Página Inicial"

        // Referência do botão logout (crie no activity_home.xml com id btnLogout)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            // Volta para o login e fecha a HomeActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
