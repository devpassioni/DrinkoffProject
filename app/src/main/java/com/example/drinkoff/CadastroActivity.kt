package com.example.drinkoff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.File

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastropage)

        val etNome = findViewById<EditText>(R.id.etNome)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etSenha = findViewById<EditText>(R.id.etSenha)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        btnCadastrar.setOnClickListener {
            val nome = etNome.text.toString()
            val email = etEmail.text.toString()
            val senha = etSenha.text.toString()

            if (nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty()) {
                val novoUsers = Users(nome, email, senha)
                salvarUsuario(novoUsers)

                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun salvarUsuario(users: Users) {
        val gson = Gson()
        val file = File(filesDir, "usuarios.json")

        val usuarios = if (file.exists()) {
            val json = file.readText()
            gson.fromJson(json, Array<Users>::class.java)?.toMutableList() ?: mutableListOf()
        } else {
            mutableListOf()
        }

        usuarios.add(users)
        file.writeText(gson.toJson(usuarios))
    }
}
