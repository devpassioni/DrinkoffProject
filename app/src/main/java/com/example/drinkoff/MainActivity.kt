package com.example.drinkoff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import java.io.File

data class User(val nome: String, val email: String, val senha: String)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login)

        // Ajuste de padding do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Criar os arquivos JSON se não existirem
        criarArquivoJsonSeNaoExistir("usuarios.json")
        criarArquivoJsonSeNaoExistir("cadastro.json")

        // Referências dos componentes
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etSenha = findViewById<EditText>(R.id.etSenha)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvCadastro = findViewById<TextView>(R.id.tvCadastro)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val senha = etSenha.text.toString().trim()

            if (email.isNotEmpty() && senha.isNotEmpty()) {
                if (validarLogin(email, senha)) {
                    Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    // Aqui alterei para abrir a HomeActivity
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Email ou senha inválidos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Preencha email e senha", Toast.LENGTH_SHORT).show()
            }
        }

        tvCadastro.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }

    private fun criarArquivoJsonSeNaoExistir(nomeArquivo: String, conteudoInicial: String = "[]") {
        val arquivo = File(filesDir, nomeArquivo)
        if (!arquivo.exists()) {
            arquivo.writeText(conteudoInicial)
        }
    }

    private fun validarLogin(email: String, senha: String): Boolean {
        val arquivo = File(filesDir, "usuarios.json")
        if (!arquivo.exists()) return false

        val json = arquivo.readText()
        val User = Gson().fromJson(json, Array<Users>::class.java)

        return User.any { it.email == email && it.senha == senha }
    }
}
