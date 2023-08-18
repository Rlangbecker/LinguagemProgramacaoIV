package br.com.fundatec.fundatecheroesti21.character.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.fundatec.fundatecheroesti21.R
import br.com.fundatec.fundatecheroesti21.character.presentation.CharacterViewModel
import br.com.fundatec.fundatecheroesti21.databinding.ActivityCharcaterBinding

class CharacterActivity : AppCompatActivity() {
    private val viewModel: CharacterViewModel by viewModels()
    private lateinit var binding: ActivityCharcaterBinding
    private lateinit var nameEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var imageEditText: EditText
    private lateinit var universeTypeEditText: EditText
    private lateinit var characterTypeEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var birthdayEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charcater)

        nameEditText = findViewById(R.id.nameEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        imageEditText = findViewById(R.id.imageEditText)
        universeTypeEditText = findViewById(R.id.universeTypeEditText)
        characterTypeEditText = findViewById(R.id.characterTypeEditText)
        ageEditText = findViewById(R.id.ageEditText)
        birthdayEditText = findViewById(R.id.birthdayEditText)

        val postButton: Button = findViewById(R.id.postButton)
        postButton.setOnClickListener {
            postCharacter()
        }
    }

    private fun postCharacter() {
        binding.postButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()
            val image = binding.imageEditText.text.toString()
            val universe = binding.universeTypeEditText.text.toString()
            val type = binding.characterTypeEditText.text.toString()
            val age = binding.ageEditText.text.toString()
            val birthday = binding.birthdayEditText.text.toString()

            viewModel.createCharacter(name, description, image, universe, type ,age, birthday)
        }
    }

//    private fun postCharacter() {
//        val characterResponse = CharacterRequest(
//            name = nameEditText.text.toString(),
//            description = descriptionEditText.text.toString(),
//            image = imageEditText.text.toString(),
//            universeType = universeTypeEditText.text.toString(),
//            characterType = characterTypeEditText.text.toString(),
//            age = ageEditText.text.toString().toInt(),
//            birthday = birthdayEditText.text.toString()
//        )
//
//        RetrofitNetworkClient.instance.getCharacter(characterResponse).enqueue(object :
//            Callback<CharacterResponse> {
//            override fun onResponse(call: Response<CharacterResponse>, response: Response<CharacterResponse>) {
//                if (response.isSuccessful) {
//                    Log.d("Success", "CharacterResponse created!")
//                } else {
//                    Log.e("Error", "Failed to create character.")
//                }
//            }
//
//            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
//                Log.e("Failure", t.message ?: "Unknown error")
//            }
//        })
//    }
}
