package br.com.fundatec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val etName = findViewById<EditText>(R.id.editview)
        val btOk = findViewById<Button>(R.id.button_ok)
        val tvHello = findViewById<TextView>(R.id.tview_nome)
        val btClear = findViewById<Button>(R.id.button_limpar)



        btOk.setOnClickListener {
            tvHello.text = getString(R.string.seu_nome, etName.text)
            tvHello.visibility = View.VISIBLE
        }

        btClear.setOnClickListener{
            tvHello.visibility = View.GONE
            etName.text.clear()
        }
    }
}

//        val car = Car(
//            2,
//            4,
//            "V8"
//        )
//


//data class Car(
//    val rodas:Int,
//    val portas:Int,
//    val motor:String
//)
//
//sealed class Result {
//    data class Success(val parafuso : String) : Result()
//    object Loading : Result()
//    object Error : Result()
//}