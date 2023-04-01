package br.com.fundatec

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val viewModel = MainViewModel()
        val intent_logado = Intent(this, LogadoActivity::class.java)
        val intent_cadastro = Intent(this, CadastroActivity::class.java)


        val et_user = findViewById<EditText>(R.id.et_user)
        val et_senha = findViewById<EditText>(R.id.et_senha)
        val bt_login = findViewById<Button>(R.id.bt_login)
        val bt_cadastro = findViewById<TextView>(R.id.bt_cadastro)


        bt_login.setOnClickListener {
         viewModel.validateName(et_user.text,et_senha.text)
        }
        viewModel.visibility.observe(this){
            startActivity(intent_logado)
            finish()
        }

        bt_cadastro.setOnClickListener {
                startActivity(intent_cadastro)
                finish()
        }


        viewModel.showToast.observe(this) {
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_LONG).show()
        }
    }


}