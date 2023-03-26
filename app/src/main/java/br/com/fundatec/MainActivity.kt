package br.com.fundatec

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = MainViewModel()
        val intent = Intent(this, LogadoActivity::class.java)


        val et_user = findViewById<EditText>(R.id.et_user)
        val et_senha = findViewById<EditText>(R.id.et_senha)
        val bt_login = findViewById<Button>(R.id.bt_login)
        val bt_cadastro = findViewById<Button>(R.id.bt_cadastro)


        bt_login.setOnClickListener {
          val verificador = viewModel.validateName(et_user.text,et_senha.text)
            if(verificador) {
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(intent)
                    finish()
                }, 1000)
            }
        }

        bt_cadastro.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(intent)
                finish()
            }, 1000)
        }


        viewModel.showToast.observe(this) {
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_LONG).show()
        }
    }


}