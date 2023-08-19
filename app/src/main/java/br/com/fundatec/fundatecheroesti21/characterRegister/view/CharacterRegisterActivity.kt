package br.com.fundatec.fundatecheroesti21.characterRegister.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.fundatec.core.hide
import br.com.fundatec.core.show
import br.com.fundatec.fundatecheroesti21.R
import br.com.fundatec.fundatecheroesti21.characterRegister.presentation.CharacterRegisterViewModel
import br.com.fundatec.fundatecheroesti21.character.presentation.model.CharacterViewState
import br.com.fundatec.fundatecheroesti21.databinding.ActivityCharacterRegisterBinding
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class CharacterRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterRegisterBinding
    private lateinit var imgHeroEditText: EditText
    private lateinit var imagePreview: ImageView

    private val viewModel: CharacterRegisterViewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        binding = ActivityCharacterRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()

        imgHeroEditText = findViewById(binding.imgHero.id)
        imagePreview = findViewById(binding.imgHeroeIv.id)

        imgHeroEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val imgUrl = s.toString()
                if (imgUrl.isNotEmpty()) {
                    LoadImageTask().execute(imgUrl)
                }
            }
        })

        validateData()
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                CharacterViewState.ShowHomeScreen -> showHome()
                CharacterViewState.ShowLoading -> showLoading()
                CharacterViewState.ShowNameError -> showNameError()
                CharacterViewState.ShowMessageError -> showSnackError()
                CharacterViewState.ShowDescriptionError -> showDescriptionError()
                CharacterViewState.ShowAgeError -> showAgeError()
                CharacterViewState.ShowBirthDateError -> showBirthDateError()
                CharacterViewState.ShowImageError -> showImageError()
            }
        }
    }

    private inner class LoadImageTask : AsyncTask<String, Void, Bitmap?>() {
        override fun doInBackground(vararg urls: String): Bitmap? {
            val imgUrl = urls[0]
            return try {
                val url = URL(imgUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input: InputStream = connection.inputStream
                BitmapFactory.decodeStream(input)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result != null) {
                imagePreview.setImageBitmap(result)
            }
        }
    }

    private fun showLoading() {
        binding.pbLoading.show()
    }

    private fun showImageError() {
        binding.imgHero.error = getString(R.string.image_error_message)
    }

    private fun showAgeError() {
        binding.pbLoading.hide()
        binding.age.error = getString(R.string.register_age_error_message)
    }

    private fun showBirthDateError() {
        binding.pbLoading.hide()
        binding.date.error = getString(R.string.register_birthdate_error_message)
    }

    private fun showNameError() {
        binding.pbLoading.hide()
        binding.nameHero.error = getString(R.string.register_name_error_message)
    }

    private fun showHome() {
        binding.pbLoading.hide()
        finish()
    }

    private fun showSnackError() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.login_error2_message, Snackbar.LENGTH_LONG).show()
    }

    private fun showDescriptionError() {
        binding.pbLoading.hide()
        binding.description.error = getString(R.string.register_description_error_message)
    }

    private fun validateData() {
        binding.floatingButton.setOnClickListener {
            viewModel.validateInputs(
                image = binding.imgHero.text.toString(),
                name = binding.nameHero.text.toString(),
                description = binding.description.text.toString(),
                universeType = binding.selectCompany.selectedItem.toString(),
                characterType = binding.selectHero.selectedItem.toString(),
                age = binding.age.text.toString(),
                birth_date = binding.date.text.toString()

            )
        }
    }

}