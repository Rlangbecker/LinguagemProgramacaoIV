package br.com.fundatec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fundatec.databinding.ActivityCadastroBinding
import br.com.fundatec.databinding.ActivityLogadoBinding

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.icBack1.setOnClickListener {
            val navegar_home = Intent(this, LoginActivity::class.java)
            startActivity(navegar_home)
            finish()
        }
    }
}