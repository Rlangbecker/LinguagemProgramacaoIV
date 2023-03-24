package br.com.fundatec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = MainViewModel()

        val etName = findViewById<EditText>(R.id.editview)
        val btOk = findViewById<Button>(R.id.button_ok)
        val tvHello = findViewById<TextView>(R.id.tview_nome)
        val btClear = findViewById<Button>(R.id.button_limpar)

        btOk.setOnClickListener {
            viewModel.validateName(etName.text)
        }

        btClear.setOnClickListener {
            viewModel.clear()
        }

        viewModel.publicName.observe(this) { name ->
            tvHello.text = getString(R.string.seu_nome, name)
            etName.setText(name)
        }

        viewModel.visibility.observe(this){ visibility ->
            tvHello.visibility = visibility
        }

        viewModel.showToast.observe(this) {
            Toast.makeText(this, "Insira um nome", Toast.LENGTH_LONG).show()
        }
    }
}