package com.example.drinkoff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VoucherGeradoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_concluido)

        val voucher = intent.getStringExtra("voucher") ?: "N/A"
        val txtVoucher = findViewById<TextView>(R.id.txtVoucherExibido)
        txtVoucher.text = "Voucher: $voucher"

        val btnVoltar = findViewById<Button>(R.id.btnVoltarHome)
        btnVoltar.setOnClickListener {
            val intent = Intent(this, PromocoesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}
