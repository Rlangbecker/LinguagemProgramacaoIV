package br.com.fundatec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fundatec.databinding.ActivityLogadoBinding
import br.com.fundatec.databinding.ActivityMainBinding

class LogadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLogadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.icBack2.setOnClickListener {
            val navegar_home = Intent(this,LoginActivity::class.java)
            startActivity(navegar_home)
            finish()
        }

    }
}