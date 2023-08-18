package br.com.fundatec.fundatecheroesti21.character.view

import CharacterViewModel
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import br.com.fundatec.core.hide
import br.com.fundatec.core.show
import br.com.fundatec.fundatecheroesti21.R
import br.com.fundatec.fundatecheroesti21.character.presentation.model.CharacterViewState
import br.com.fundatec.fundatecheroesti21.databinding.ActivityCharcaterBinding
import br.com.fundatec.fundatecheroesti21.home.view.HomeActivity
import com.google.android.material.snackbar.Snackbar

class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharcaterBinding
    private val viewModel: CharacterViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharcaterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()
        setupButtonClicks()
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                is CharacterViewState.ShowLoading -> showLoading()
                is CharacterViewState.Success -> navigateNewCharacter()
                is CharacterViewState.ShowHomeScreen -> showHome()
                else -> {
                    Toast.makeText(this, "Erro desconhecido!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupButtonClicks() {
        binding.floatingButton.setOnClickListener {
            val name = binding.characterName.text.toString()
            val description = binding.characterDescription.text.toString()
            val age = binding.characterAge.text.toString()
            val birthday = binding.characterBirthday.text.toString()

            viewModel.createCharacter(name, description, age, birthday)
        }
    }

    private fun navigateNewCharacter() {
        val intent = Intent(this@CharacterActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun showLoading() {
        binding.pbLoading.show()
    }

    private fun showAgeError() {
        binding.pbLoading.hide()
        binding.characterAge.error = getString(R.string.register_age_error_message)
    }

    private fun showBirthDateError() {
        binding.pbLoading.hide()
        binding.characterBirthday.error = getString(R.string.register_birthdate_error_message)
    }

    private fun showNameError() {
        binding.pbLoading.hide()
        binding.characterName.error = getString(R.string.register_name_error_message)
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
        binding.characterDescription.error = getString(R.string.register_description_error_message)
    }


}