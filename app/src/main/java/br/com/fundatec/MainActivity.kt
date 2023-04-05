package br.com.fundatec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts.People.Extensions
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.components.showSnack
import com.example.components.showToast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = MainViewModel()

        val etName = findViewById<EditText>(R.id.editview)
        val btOk = findViewById<Button>(R.id.button_ok)
        val tvHello = findViewById<TextView>(R.id.tview_nome)
        val btClear = findViewById<Button>(R.id.button_limpar)
        val container = findViewById<ConstraintLayout>(R.id.main_container)


        btOk.setOnClickListener {
            viewModel.validateName(etName.text)
        }

        btClear.setOnClickListener {
            showSnack(container,"limpado tela")
            viewModel.clear()
        }

        viewModel.publicName.observe(this) { name ->
            tvHello.text = getString(R.string.seu_nome, name)
        }

        viewModel.visibility.observe(this) { visibility ->
            tvHello.visibility = visibility
//            tvHello.visible()
        }


        viewModel.showToast.observe(this) {
            showToast("Preencha os campos!!!")
        }


    }
}