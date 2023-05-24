package br.com.fundatec.fundatecheroesti21.home.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fundatec.fundatecheroesti21.character.view.CharcaterActivity
import br.com.fundatec.fundatecheroesti21.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = HomeViewPager(supportFragmentManager)

        binding.homeViewPager.adapter = adapter

        binding.homeTabLayout.setupWithViewPager(binding.homeViewPager)

        binding.btNewCharacter.setOnClickListener {
            val intent = Intent(this@HomeActivity, CharcaterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

